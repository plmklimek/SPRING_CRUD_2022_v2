package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.mappers.UserMapper;
import com.example.demo.services.impl.EventServiceImpl;
import com.example.demo.services.impl.UserServiceImpl;
import com.example.demo.utills.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserServiceImpl userService;

    private final EventServiceImpl eventService;

    @GetMapping("/user/{id}")
    public ResponseEntity login(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(UserMapper.mapUserToUserDto(userService.getById(id)));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(UserMapper.mapUsersToUsersDto(userService.getAll()));
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
            HashMap<String, String> map = new HashMap<>();
            map.put("mail", userService.login());
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/usersAvailable/{id}")
    public ResponseEntity getUsersAvailableForEvent(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(UserMapper.mapUsersToUsersDto(eventService.getAvailableForEvent(id)));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity getMe() {
        try{
            String loggedUser = LoggedUser.getLoggedUser();
            return ResponseEntity.status(HttpStatus.OK).body(UserMapper.mapUserToUserDto(userService.getByEmail(loggedUser)));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }
}
