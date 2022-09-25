package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/user/{id}")
    public ResponseEntity login(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto userDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.register(userDto));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.login());
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }
}
