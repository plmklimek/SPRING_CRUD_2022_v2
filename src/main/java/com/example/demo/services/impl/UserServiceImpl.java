package com.example.demo.services.impl;

import com.example.demo.dtos.UserDto;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Authority;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_DOESNT_EXIST = "USER DOESNT EXIST";

    private final UserRepository userRepository;

    private final AuthorityServiceImpl authorityService;

    @Override
    public User register(UserDto userDto) {
        User user = UserMapper.mapUserDtoToUser(userDto);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        User save = userRepository.save(user);
        Authority auth = Authority.builder()
                .authority("USER")
                .user(save)
                .build();
        authorityService.create(auth);
        return save;
    }

    @Override
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public Set<User> getAll() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(USER_DOESNT_EXIST));
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
