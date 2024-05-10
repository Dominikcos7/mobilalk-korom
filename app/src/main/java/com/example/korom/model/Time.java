package com.example.korom.model;

import androidx.annotation.NonNull;

public class Time {
    private int hour;
    private int minute;

    public Time() {
    }

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @NonNull
    @Override
    public String toString() {
        String hour, minute;
        hour = this.hour >= 10 ? String.valueOf(this.hour) : "0" + this.hour;
        minute = this.minute >= 10 ? String.valueOf(this.minute) : "0" + this.minute;
        return hour + ":" + minute;
    }
}
