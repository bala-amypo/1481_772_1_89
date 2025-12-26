 package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

public class UserServiceImpl {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Dummy methods for compilation
}
