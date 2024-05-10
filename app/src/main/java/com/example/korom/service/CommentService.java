package com.example.korom.service;

import com.example.korom.model.Comment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class CommentService {
    private static CommentService instance;
    private static final String collectionName = "comments";
    private FirebaseFirestore mFirestore;

    private CommentService() {
        mFirestore = FirebaseFirestore.getInstance();
    }

    public static CommentService getInstance(){
        if(instance == null)
            return new CommentService();
        else return instance;
    }

    public Task<QuerySnapshot> getAll(){
        return mFirestore.collection(collectionName).get();
    }

    public Task<DocumentReference> addComment(Comment comment){
        return mFirestore.collection(collectionName).add(comment);
    }
}
