package com.example.demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OwnUserDetails implements UserDetails {
    private Long id;

    private String email;

    private String password;

    private String token;

    private Set<Authority> authorities;

    public OwnUserDetails(Long id, String email, String password, String token, Set<Authority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.token = token;
        this.authorities = authorities;
    }

    public static OwnUserDetails build(User user) {
        return new OwnUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getToken(),
                user.getAuthorities());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        this.authorities.forEach(authority -> list.add(new SimpleGrantedAuthority("ROLE_" + authority)));

        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnUserDetails user = (OwnUserDetails) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && Objects.equals(token, user.token)
                && Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, token, authorities);
    }
}
