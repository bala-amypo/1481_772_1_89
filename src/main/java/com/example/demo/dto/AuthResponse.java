 package com.example.demo.dto;

public class AuthResponse {

    private String token;
    private Long userId;
    private String fullName;
    private String role;

    // Constructor expected by your test
    public AuthResponse(String token, Long userId, String fullName, String role) {
        this.token = token;
        this.userId = userId;
        this.fullName = fullName;
        this.role = role;
    }

    // Default constructor
    public AuthResponse() {}

    // Getters
    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    // Setters (optional, for serialization)
    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
