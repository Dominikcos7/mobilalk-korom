package com.example.korom.model;

public class Appointment {
    private String date;
    private String time;
    private String description;
    private String userId;
    private String id;

    public Appointment() {
    }

    public Appointment(String date, String time, String description, String userId, String id) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.userId = userId;
        this.id = id;
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
