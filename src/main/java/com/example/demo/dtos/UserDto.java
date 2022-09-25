package com.example.demo.dtos;

import com.example.demo.models.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private String token;

    private Set<Authority> authorities;

    private Set<InvitationDto> invitations = new HashSet<>();

    private Set<EventDto> events = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && Objects.equals(token, userDto.token) && Objects.equals(authorities, userDto.authorities) && Objects.equals(invitations, userDto.invitations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, token, authorities, invitations);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", authorities=" + authorities +
                ", invitations=" + invitations +
                '}';
    }
}
