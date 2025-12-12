package com.vibetrack.controller;

import com.vibetrack.entity.User;
import com.vibetrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}
