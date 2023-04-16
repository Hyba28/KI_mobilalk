package com.example.ki_mobilalk;

public interface Callback<T> {
    void onSuccess(T result);
    void onFailure(Exception e);
}