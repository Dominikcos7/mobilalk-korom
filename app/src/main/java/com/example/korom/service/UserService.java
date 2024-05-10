package com.example.korom.service;

import android.util.Log;

import com.example.korom.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.atomic.AtomicBoolean;

public class UserService {
    private static final String TAG = UserService.class.getName();
    private static final String collelctionName = "users";
    private static UserService instance;
    private FirebaseFirestore mFirestore;

    private UserService(){
        this.mFirestore = FirebaseFirestore.getInstance();
    }

    public static UserService getInstance(){
        if(instance == null)
            return new UserService();
        else return instance;
    }

    public Task<Void> addUser(User user){
        return mFirestore.collection(collelctionName).document(user.getEmail()).set(user);
    }

    public boolean addUserIfNotExists(User user){
        AtomicBoolean success = new AtomicBoolean(false);
        DocumentReference docRef = mFirestore.collection(collelctionName).document(user.getEmail());
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if(!documentSnapshot.exists()){
                addUser(user).addOnSuccessListener(_void -> {
                    success.set(true);
                }).addOnFailureListener(e -> {
                    Log.e(TAG,e.toString());
                });
            }
        }).addOnFailureListener(e -> {
            Log.i(TAG, e.toString());
        });

        return success.get();
    }

    public Task<DocumentSnapshot> getUsername(String email){
        return mFirestore.collection(collelctionName).document(email).get();
    }
}
