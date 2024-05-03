package com.example.korom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameET;
    private EditText passwordET;
    private EditText passwordAgainET;
    private EditText emailET;
    private FirebaseAuth mAuth;
    private static final String TAG = RegisterActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = findViewById(R.id.username);
        passwordET = findViewById(R.id.password);
        passwordAgainET = findViewById(R.id.passwordAgain);
        emailET = findViewById(R.id.email);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String passwordAgain = passwordAgainET.getText().toString();
        String email = emailET.getText().toString();

        if(passwordAgain.equals(password)){
            mAuth.createUserWithEmailAndPassword(email, passwordAgain).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.i(TAG, "ok");
                        Intent intent = new Intent(RegisterActivity.this, IndexActivity.class);
                        startActivity(intent);
                    } else {
                        Log.i(TAG, "error" + task.getException().getMessage());
                        Toast.makeText(RegisterActivity.this, "error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}