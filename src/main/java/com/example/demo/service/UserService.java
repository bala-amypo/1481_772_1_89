 package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    // Dummy in-memory user store (replace with JPA repository in real app)
    private final Map<String, User> users = new HashMap<>();

    public UserService() {
        // Preload a test user (password in plain text for simplicity)
        User user = new User();
        user.setId(1L);
        user.setFullName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password"); // In real app, store encoded passwords
        user.setRole("USER");
        users.put(user.getEmail(), user);
    }

    public User findByEmail(String email) {
        User user = users.get(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
}
