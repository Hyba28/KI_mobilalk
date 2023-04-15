package com.example.ki_mobilalk;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

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

    public void add(FirebaseUser firebaseUser, String realName, String nickName, FirestoreCallback firestoreCallback) {
        User user = new User(firebaseUser.getUid(), nickName, realName, new ArrayList<>());
        db.document(firebaseUser.getUid()).set(user);
        firestoreCallback.onCallbackOne(user);
    }

    public void get(FirebaseUser user, String realName, String nickName, FirestoreCallback firestoreCallback) {
        DocumentReference docRef = db.document(user.getUid());
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                firestoreCallback.onCallbackOne(task.getResult().toObject(User.class));
            } else {
                add(user,realName, nickName, firestoreCallback);
            }
        });
    }

    public void getAll(FirestoreCallback firestoreCallback) {
        List<User> users = new ArrayList<>();
        db.orderBy("realName").get().addOnCompleteListener(task -> {
            for (QueryDocumentSnapshot ref : task.getResult()) {
                users.add(ref.toObject(User.class));
            }
            firestoreCallback.onCallbackMore(users);
        });
    }

    public void delete(String uid, Context context) {
        db.document(uid).delete().addOnCompleteListener(task -> Toast.makeText(context, "User sikeresen törölve.", Toast.LENGTH_LONG).show());
    }

    public void update(FirebaseUser firebaseUser, String field, String value) {
           if(!field.equals("uid")) {
               db.document(firebaseUser.getUid()).update(field, value);
           }
    }

}




