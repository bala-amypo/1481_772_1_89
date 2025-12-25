package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

     @PostMapping("/login")
public AuthResponse login(@RequestBody AuthRequest request) {

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()));

    User user = userService.findByEmail(request.getEmail());

    String token = jwtUtil.generateToken(
            new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of()), // authorities
            user
    );

    return new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getRole()
    );
}
