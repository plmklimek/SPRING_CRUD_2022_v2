package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;
import com.example.demo.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/user/{id}")
    public User login(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/users")
    public Set<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public String login() {
        return userService.login();
    }
}
