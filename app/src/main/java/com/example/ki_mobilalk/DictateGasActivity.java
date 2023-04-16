package com.example.ki_mobilalk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class DictateGasActivity extends AppCompatActivity {
    private static final String LOG_TAG = DictateGasActivity.class.getName();
    private FirebaseAuth mAuth;
    private UserDAO userDAO;
    private EditText gasmeter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictategas);

        mAuth = FirebaseAuth.getInstance();
        userDAO = new UserDAO(this);
        Button submit = findViewById(R.id.button4);
        Button cancel = findViewById(R.id.button7);
        submit.setOnClickListener(this::submit);
        cancel.setOnClickListener(this::cancel);
        gasmeter = findViewById(R.id.gasMeter);


    }

    private void submit(View view) {
        String gasmeterValueString = gasmeter.getText().toString();
        int gasmeterValue = Integer.parseInt(gasmeterValueString);
        Intent intent = new Intent(this, ChooseActivity.class);
        userDAO.updateValue(Objects.requireNonNull(mAuth.getCurrentUser()), gasmeterValue, new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                Log.d(LOG_TAG, "Value updated successfully.");
                // handle success

            }

            @Override
            public void onFailure(Exception e) {
                Log.w(LOG_TAG, "Error updating value", e);
                // handle failure

            }
        });
 // ido beallitas, magatol tortenik
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userDAO.updateDate(Objects.requireNonNull(mAuth.getCurrentUser()), dateFormat.format(calendar.getTime()), new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                Log.d(LOG_TAG, "Value updated successfully.");
                // handle success
                startActivity(intent);

            }

            @Override
            public void onFailure(Exception e) {
                Log.w(LOG_TAG, "Error updating value", e);
                // handle failure

            }
        });




    }

    private void cancel(View view) {
        Intent intent = new Intent(this, ChooseActivity.class);
        startActivity(intent);
    }




    }


