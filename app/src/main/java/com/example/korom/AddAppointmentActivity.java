package com.example.korom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.korom.dialog.DatePickerFragment;
import com.example.korom.dialog.TimePickerFragment;
import com.example.korom.model.Appointment;
import com.example.korom.service.AppointmentService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddAppointmentActivity extends AppCompatActivity {
    private static final String TAG = AddAppointmentActivity.class.getName();
    private AppointmentInstance appointmentInstance;
    private AppointmentService appointmentService;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        user = FirebaseAuth.getInstance().getCurrentUser();
        authenticate();

        appointmentInstance = AppointmentInstance.getInstance();
        appointmentInstance.setData(new Appointment());
        appointmentService = AppointmentService.getInstance();
    }

    private void authenticate(){
        if(this.user == null)
            finish();
    }
    public void openDateDialog(View view) {
        DatePickerFragment dpf = new DatePickerFragment();
        dpf.show(getSupportFragmentManager(), "datepicker");
    }

    public void openTimeDialog(View view) {
        TimePickerFragment tpf = new TimePickerFragment();
        tpf.show(getSupportFragmentManager(), "timepicker");
    }

    public void addAppointment(View view) {
        if(appointmentInstance.getDate() != null && appointmentInstance.getTime() != null){
            EditText descriptionET = findViewById(R.id.addAppointmentDescription);
            String description = descriptionET.getText().toString();
            String id = appointmentService.getNewId();

            appointmentInstance.setDescription(description);
            appointmentInstance.setUserId(user.getEmail());
            appointmentInstance.setId(id);

            Appointment appointment = appointmentInstance.getData();

            appointmentService.add(appointment).addOnSuccessListener(documentReference -> {
                Log.i(TAG, "successful appointment addition");
                setResult(0, null);
                finish();
            }).addOnFailureListener(e -> {
                Log.e(TAG, e.toString());
            });
        } else {
            Toast.makeText(this, "Select date and time!", Toast.LENGTH_LONG).show();
        }
    }
}