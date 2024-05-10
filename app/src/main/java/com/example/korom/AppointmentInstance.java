package com.example.korom;

import com.example.korom.model.Appointment;

public class AppointmentInstance {
    private String date;
    private String time;
    private String description;
    private String userId;
    private String id;

    private static AppointmentInstance instance;

    public static AppointmentInstance getInstance(){
        if(instance == null)
            instance = new AppointmentInstance();
        return instance;
    }

    public void setData(Appointment appointment){
        time = appointment.getTime();
        date = appointment.getDate();
        userId = appointment.getUserId();
        description = appointment.getDescription();
        id = appointment.getId();
    }

    public Appointment getData(){
        return new Appointment(date, time, description, userId, id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
