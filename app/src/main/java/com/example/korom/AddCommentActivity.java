package com.example.korom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.korom.model.Comment;
import com.example.korom.model.User;
import com.example.korom.service.CommentService;
import com.example.korom.service.UserService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddCommentActivity extends AppCompatActivity {
    private static final String TAG = AddCommentActivity.class.getName();
    private CommentService commentService;
    private UserService userService;
    private FirebaseAuth mAuth;
    private EditText mContent;
    private RatingBar mRatingBar;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);



        mAuth = FirebaseAuth.getInstance();
        mContent = findViewById(R.id.addCommentContentEditText);
        mRatingBar = findViewById(R.id.addCommentRatingBar);
        mUser = mAuth.getCurrentUser();
        commentService = CommentService.getInstance();
        userService = UserService.getInstance();

        authenticateUser();
    }

    private void authenticateUser(){
        if(mUser == null)
            finish();
    }

    public void addComment(View view) {
        String email = mAuth.getCurrentUser().getEmail();
        userService.getUsername(email).addOnSuccessListener(documentSnapshot -> {
            Comment comment = new Comment(
                    email,
                    (String) documentSnapshot.get("username"),
                    mRatingBar.getRating(),
                    mContent.getText().toString());
            commentService.addComment(comment).addOnSuccessListener(this, documentReference -> {
                Log.i(TAG, "successful comment addition");
                setResult(0, null);
                finish();
            }).addOnFailureListener(this, e -> {
                Log.e(TAG, e.toString());
            });
        }).addOnFailureListener(e -> {
            Log.e(TAG, e.toString());
        });
    }
}