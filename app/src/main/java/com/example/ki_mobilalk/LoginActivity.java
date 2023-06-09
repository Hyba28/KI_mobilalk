package com.example.ki_mobilalk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {


    private static final String LOG_TAG = LoginActivity.class.getName();


    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_login);

        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
        Button login = findViewById(R.id.button2);
        Button cancel = findViewById(R.id.button3);

        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        login.startAnimation(fade);
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_in);
        cancel.startAnimation(rotate);

        login.setOnClickListener(this::login);
        cancel.setOnClickListener(this::cancel);



    }

    private void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void login(View view) {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        if (emailText.equals("") || passwordText.equals("")) {
            Toast.makeText(this, "Üres valamelyik mező", Toast.LENGTH_LONG).show();
        } else{
            mAuth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.d(LOG_TAG, "sikeres bejelentkezes!");
                            Intent intent = new Intent(this, ChooseActivity.class);
                            startActivity(intent);
                        } else {
                            Log.w(LOG_TAG, "sikertelen bejelentkezes", task.getException());
                            Toast.makeText(LoginActivity.this, "Sikertelen belépés",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }


}
