 package com.example.demo.dto;

public class AuthResponse {

    private String token;
    private long userId;
    private String email;
    private String role;

    public AuthResponse() {} // default

    // 4-args constructor (required by test)
    public AuthResponse(String token, long userId, String email, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    // Getters
    public String getToken() { return token; }
    public long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    // Setters
    public void setToken(String token) { this.token = token; }
    public void setUserId(long userId) { this.userId = userId; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
}
