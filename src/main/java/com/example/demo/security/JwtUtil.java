 package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "secret123";
    private final long EXPIRATION = 1000 * 60 * 60;

    // Test expects this signature
    public String generateToken(UserDetails userDetails, com.example.demo.model.User user) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
