 package com.example.demo.dto;

public class AuthResponse {
    private String token;
    private Long userId;
    private String fullName;
    private String role;

    public AuthResponse() {}

    public AuthResponse(String token, Long userId, String fullName, String role) {
        this.token = token;
        this.userId = userId;
        this.fullName = fullName;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
