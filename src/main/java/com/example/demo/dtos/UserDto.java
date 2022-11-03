package com.example.demo.dtos;

import com.example.demo.models.Authority;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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

    @JsonBackReference("invitation")
    private List<InvitationDto> invitations = new ArrayList<>();

    @JsonManagedReference("event")
    private List<EventDto> events = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(email, userDto.email) && Objects.equals(password, userDto.password) && Objects.equals(token, userDto.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, token);
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
