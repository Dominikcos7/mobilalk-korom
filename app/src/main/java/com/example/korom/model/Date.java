package com.example.korom.model;

import androidx.annotation.NonNull;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date() {
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @NonNull
    @Override
    public String toString() {
        String month, day;
        month = this.month >= 10 ? String.valueOf(this.month) : "0" + this.month;
        day = this.day >= 10 ? String.valueOf(this.day) : "0" + this.day;
        return this.year + "." + month + "." + day + ".";
    }
}
