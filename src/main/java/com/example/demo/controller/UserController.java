 package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users Endpoints")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/all")
    public List<User> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }
}
