package com.example.korom.service;


import com.example.korom.model.Appointment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AppointmentService {
    private static final String TAG = AppointmentService.class.getName();
    private static AppointmentService instance;
    private FirebaseFirestore mFirestore;
    private final String collectionName = "appointments";

    private AppointmentService(){
        mFirestore = FirebaseFirestore.getInstance();
    }
    public static AppointmentService getInstance(){
        if(instance == null)
            instance = new AppointmentService();
        return instance;
    }

    public Task<QuerySnapshot> getAll(String id){
        return mFirestore.collection(collectionName).whereEqualTo("userId", id).get();
    }

    public String getNewId(){
        return mFirestore.collection(collectionName).document().getId();
    }

    public Task<Void> add(Appointment appointment){
        return mFirestore.collection(collectionName).document(appointment.getId()).set(appointment);
    }

    public Task<Void> delete(String id){
        return mFirestore.collection(collectionName).document(id).delete();
    }
}
