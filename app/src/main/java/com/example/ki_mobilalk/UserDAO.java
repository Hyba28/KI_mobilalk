package com.example.ki_mobilalk;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserDAO {


    private static final String LOG_TAG = User.class.getName();
    private final CollectionReference db;
    private Context context;


    public UserDAO(Context context) {
        this.db = FirebaseFirestore.getInstance().collection("users");
        this.context = context;
    }

    public Task<Void> add(@NonNull User user) {
        return db.document(user.getId()).set(user);
    }


    public void getValue(FirebaseUser user, final Callback<Integer> callback) {
        DocumentReference docRef = db.document(user.getUid());
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    int value = Objects.requireNonNull(document.getLong("value")).intValue();
                    Log.d(LOG_TAG, "Value: " + value);
                    callback.onSuccess(value);
                } else {
                    Log.d(LOG_TAG, "No such document");
                }
            } else {
                Log.d(LOG_TAG, "get failed with ", task.getException());
            }
        });
    }

    public void getDate(FirebaseUser user, Callback<String> callback) {
        DocumentReference docRef = db.document(user.getUid());
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String date = document.getString("dictateDate");
                    callback.onSuccess(date);
                } else {
                    callback.onFailure(new Exception("No such document"));
                }
            } else {
                callback.onFailure(task.getException());
            }
        });
    }



    public void delete(String uid, Context context) {
        db.document(uid).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "User sikeresen törölve.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Hiba történt a felhasználó törlésekor.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateValue(FirebaseUser user, int value, final Callback<Void> callback) {
        getValue(user, new Callback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                if (value >= result) {
                    DocumentReference docRef = db.document(user.getUid());
                    docRef.update("value", value)
                            .addOnSuccessListener(aVoid -> {
                                Log.d(LOG_TAG, "Value updated successfully.");
                                callback.onSuccess(null);
                            })
                            .addOnFailureListener(e -> {
                                Log.w(LOG_TAG, "Error updating value", e);
                                callback.onFailure(e);
                            });
                } else {
                    Toast.makeText(context.getApplicationContext(), "A megadott érték kisebb, mint az előző érték! Nem lehet visszatekerni!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                // hiba kezelése
            }
        });
    }
    public void updateDate(FirebaseUser user, String date, final Callback<Void> callback) {
        DocumentReference docRef = db.document(user.getUid());
        Map<String, Object> updates = new HashMap<>();
        updates.put("dictateDate", date);
        docRef.update(updates)
                .addOnSuccessListener(aVoid -> {
                    Log.d(LOG_TAG, "DocumentSnapshot successfully updated!");
                    callback.onSuccess(null);
                })
                .addOnFailureListener(e -> {
                    Log.w(LOG_TAG, "Error updating document", e);
                    callback.onFailure(e);
                });
    }



}




