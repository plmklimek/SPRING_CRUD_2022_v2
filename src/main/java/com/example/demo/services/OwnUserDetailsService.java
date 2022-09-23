package com.example.demo.services;

import com.example.demo.models.OwnUserDetails;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class OwnUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Not found!");
        }

        return OwnUserDetails.build(user);
    }
}
