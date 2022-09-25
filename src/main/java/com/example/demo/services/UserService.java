package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;

import java.util.Set;

public interface UserService {
    User register(UserDto userDto);

    String login();

    Set<User> getAll();

    User getById(Long id);

    User getByEmail(String email);

}
