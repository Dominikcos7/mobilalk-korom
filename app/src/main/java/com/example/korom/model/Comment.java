package com.example.korom.model;

public class Comment {
    private String email;
    private String username;
    private float rating;
    private String content;

    public Comment() {
    }

    public Comment(String email, String username, float rating, String content) {
        this.email = email;
        this.username = username;
        this.rating = rating;
        this.content = content;
    }

    public String getEmail() {return email; }

    public String getUsername() {
        return username;
    }

    public float getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }
}
