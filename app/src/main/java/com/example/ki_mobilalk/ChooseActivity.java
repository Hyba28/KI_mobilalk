package com.example.ki_mobilalk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private UserDAO userDAO;
    private static final String LOG_TAG = ChooseActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser() == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        Button add = findViewById(R.id.addGas);
        Button list = findViewById(R.id.listGas);
        Button logout = findViewById(R.id.logout);
        Button delete = findViewById(R.id.delete);
        add.setOnClickListener(this::addGas);
        list.setOnClickListener(this::listGas);
        logout.setOnClickListener(this::logOut);
        delete.setOnClickListener(this::delete);




    }

    private void delete(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        user.delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userDAO = new UserDAO(this);
                        userDAO.delete(uid, this);
                        Log.d(LOG_TAG, "User account deleted.");
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                });

    }

    private void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void listGas(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        userDAO = new UserDAO(this);

        Intent intent = new Intent(this, ListGasMeterActivity.class);
        startActivity(intent);
    }

    private void addGas(View view) {
        Intent intent = new Intent(this, DictateGasActivity.class);
        startActivity(intent);

    }

}
