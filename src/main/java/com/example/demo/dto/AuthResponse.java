 package com.example.demo.dto;

public class AuthResponse {

    private String token;
    private long userId;
    private String email;
    private String role;

    // Default constructor
    public AuthResponse() {}

    // Constructor for 4 args
    public AuthResponse(String token, long userId, String email, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    // Getters & Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
