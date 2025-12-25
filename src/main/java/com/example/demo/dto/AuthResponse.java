 package com.example.demo.dto;

public class AuthResponse {
    private String token;
    private long id;
    private String email;
    private String fullName;

    public AuthResponse() {}

    // Constructor for full response
    public AuthResponse(String token, long id, String email, String fullName) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
