package com.example.demo.dtos;

import com.example.demo.models.StatusInvitation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Setter
@Getter
public class InvitationDto {
    private Long id;

    @JsonBackReference
    private EventDto event;

    @JsonBackReference("invitation")
    private UserDto user;

    private StatusInvitation status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvitationDto that = (InvitationDto) o;
        return Objects.equals(id, that.id) && Objects.equals(event, that.event) && Objects.equals(user, that.user)
                && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, user, status);
    }

    @Override
    public String toString() {
        return "InvitationDto{" +
                "id=" + id +
                ", event=" + event +
                ", user=" + user +
                ", invitation=" + status +
                '}';
    }
}
