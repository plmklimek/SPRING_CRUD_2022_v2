package com.example.demo.services.impl;

import com.example.demo.models.Authority;
import com.example.demo.repositories.AuthorityRepository;
import com.example.demo.services.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Override
    public void create(Authority authority) {
        authorityRepository.save(authority);
    }
}
