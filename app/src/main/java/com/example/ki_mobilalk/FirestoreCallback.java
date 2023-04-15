package com.example.ki_mobilalk;

import java.util.List;

public interface FirestoreCallback {

    void onCallbackOne(User user);

    void onCallbackMore(List<User> users);
}
