 package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

     @PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

    // Authenticate user
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Load user
    User user = userService.findByEmail(request.getEmail());

    // Cast principal to UserDetails
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    // Generate JWT token
    String token = jwtUtil.generateToken(userDetails, user);

    // Return AuthResponse
    AuthResponse response = new AuthResponse(
            token,
            user.getId(),
            user.getFullName(),
            user.getRole()
    );

    return ResponseEntity.ok(response);
}
