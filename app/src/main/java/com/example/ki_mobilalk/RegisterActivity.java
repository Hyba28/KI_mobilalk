package com.example.ki_mobilalk;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;


import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private EditText realName;
    private EditText nickName;
    private EditText email;
    private EditText pass1;
    private EditText pass2;

    private static final String LOG_TAG = RegisterActivity.class.getName();
    private FirebaseAuth mAuth;
    private UserDAO userDAO;
    private NotificationHandler notificationHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        notificationHandler = new NotificationHandler(this);
        userDAO = new UserDAO(this);

        setContentView(R.layout.activity_register);

        realName = findViewById(R.id.editTextTextPersonName);
        nickName = findViewById(R.id.editTextTextPersonNickname);
        email = findViewById(R.id.editTextTextEmailAddress);
        pass1 = findViewById(R.id.editTextTextPassword);
        pass2 = findViewById(R.id.editTextPasswordAgain);
        Button registerButton = findViewById(R.id.registrationButton);
        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this::onCancel);
        registerButton.setOnClickListener(this::registerWithEmail);
    }

    private void onCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void registerWithEmail(View view) {
        String emailText = email.getText().toString();
        String passText1 = pass1.getText().toString();
        String passText2 = pass2.getText().toString();

        if(passText2.equals(passText1)){
            mAuth.createUserWithEmailAndPassword(emailText, passText1)
                    .addOnCompleteListener(this, task -> {
                       if(task.isSuccessful()){
                           Log.d(LOG_TAG, "Sikeres regisztacio!");
                           FirebaseUser firebaseUser = mAuth.getCurrentUser();
                           User user = new User(firebaseUser.getUid(),nickName.getText().toString(), realName.getText().toString());
                           userDAO.add(user)
                                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void aVoid) {
                                           Log.d(LOG_TAG, "DocumentSnapshot added successfully");
                                       }
                                   })
                                   .addOnFailureListener(new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           Log.w(LOG_TAG, "Error adding document", e);
                                       }
                                   });
                           notificationHandler.sendNotification("Koszonom a regisztraciot!");
                           Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                           startActivity(intent);

                       }else{
                        Log.w(LOG_TAG, "hiba tortent a regisztracio soran...");
                        Toast.makeText(this, "Valami hiba történt!:( (talán rövid a jelszó /használatban lévő email cím)", Toast.LENGTH_LONG).show();
                       }
                    });
        }else {
            Toast.makeText(RegisterActivity.this, "Nem egyezik meg a két jelszó", Toast.LENGTH_LONG).show();
        }
    }

}
