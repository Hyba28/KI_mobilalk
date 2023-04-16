package com.example.ki_mobilalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ListGasMeterActivity extends AppCompatActivity {
    private static final String LOG_TAG = DictateGasActivity.class.getName();
    private FirebaseAuth mAuth;
    private UserDAO userDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listgas);

        mAuth = FirebaseAuth.getInstance();
        Button button = findViewById(R.id.button5);
        button.setOnClickListener(this::vissza);
        TextView gasText = findViewById(R.id.oraallas);
        TextView dateText = findViewById(R.id.diktalas);
        FirebaseUser user = mAuth.getCurrentUser();
        userDAO = new UserDAO(this);
        assert user != null;
        userDAO.getValue(user, new Callback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                Log.d(LOG_TAG, "Value: " + result);
                gasText.setText(String.valueOf(result));
            }

            @Override
            public void onFailure(Exception e) {


            }
        });

        userDAO.getDate(user, new Callback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(LOG_TAG, "Date: " + result);
                dateText.setText(result);
            }

            @Override
            public void onFailure(Exception e) {
                // kezelni lehet a hibát
            }
        });



    }

    private void vissza(View view) {
        Intent intent = new Intent(this, ChooseActivity.class);
        startActivity(intent);

    }



}
