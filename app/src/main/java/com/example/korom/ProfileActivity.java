package com.example.korom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.korom.adapter.AppointmentAdapter;
import com.example.korom.dialog.DatePickerFragment;
import com.example.korom.dialog.TimePickerFragment;
import com.example.korom.model.Appointment;
import com.example.korom.service.AppointmentService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private final static String TAG = ProfileActivity.class.getName();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private RecyclerView mRecyclerView;
    private ArrayList<Appointment> mItemList;
    private int gridNumber = 1;
    private AppointmentAdapter mAdapter;
    private AppointmentService appointmentService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        appointmentService = AppointmentService.getInstance();
        authenticateUser();
        initializeRecyclerView();
        refreshData();
    }

    private void authenticateUser(){
        user = mAuth.getCurrentUser();
        if(user == null)
            finish();
    }

    private void initializeRecyclerView(){
        mRecyclerView = findViewById(R.id.profileRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mItemList = new ArrayList<>();
        mAdapter = new AppointmentAdapter(this, mItemList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void refreshData(){
        mItemList.clear();
        appointmentService.getAll(user.getEmail()).addOnSuccessListener(queryDocumentSnapshots -> {
            List<Appointment> list = queryDocumentSnapshots.toObjects(Appointment.class);
            mItemList.addAll(list);
            mAdapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Log.e(TAG, e.toString()));
    }

    public void addAppointment(View view) {
        Intent intent = new Intent(this, AddAppointmentActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "refreshing...");
        refreshData();
    }

    public void deleteAppointment(View view) {
        Button button = findViewById(R.id.appointmentCancelButton);
        String id = (String) button.getTag();
        appointmentService.delete(id).addOnSuccessListener(_void -> {
            Log.i(TAG, "successful delete");
            refreshData();
        }).addOnFailureListener(e -> Log.e(TAG, e.toString()));
    }
}