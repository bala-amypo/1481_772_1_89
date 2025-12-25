 package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findByEmail(String email) {
        // Temporary stub for testing
        if ("test@example.com".equals(email)) {
            User user = new User();
            user.setId(1L);
            user.setFullName("Test User");
            user.setEmail(email);
            user.setPassword("password"); // in real app, password should be hashed
            user.setRole("USER");
            return user;
        }
        return null;
    }
}
