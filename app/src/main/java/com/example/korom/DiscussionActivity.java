package com.example.korom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.korom.adapter.CommentAdapter;
import com.example.korom.model.Comment;
import com.example.korom.service.CommentService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DiscussionActivity extends AppCompatActivity {
    private static final String TAG = DiscussionActivity.class.getName();
    private CommentService commentService;
    private FirebaseUser user;
    private RecyclerView mRecyclerView;
    private ArrayList<Comment> mItemList;
    private CommentAdapter mCommentAdapter;
    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        authenticateUser();
        initializeRecyclerView();

        commentService = CommentService.getInstance();

        refreshData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "refreshing...");
        refreshData();
    }

    private void authenticateUser() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            finish();
        }
    }

    private void initializeRecyclerView() {
        mRecyclerView = findViewById(R.id.discussionRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mItemList = new ArrayList<>();
        mCommentAdapter = new CommentAdapter(this, mItemList);
        mRecyclerView.setAdapter(mCommentAdapter);
    }

    private void refreshData() {
        mItemList.clear();
        commentService.getAll().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Comment> list = queryDocumentSnapshots.toObjects(Comment.class);
            mItemList.addAll(list);
            mCommentAdapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> {
            Log.e(TAG, e.toString());
        });
    }

    public void addComment(View view) {
        Intent intent = new Intent(this, AddCommentActivity.class);
        startActivityForResult(intent, 0);
    }
}