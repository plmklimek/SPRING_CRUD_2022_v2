package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDto {
    private Long id;

    private String name;

    @JsonBackReference
    private List<InvitationDto> invitations = new ArrayList<>();

    @JsonBackReference("event")
    private UserDto owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(id, eventDto.id) && Objects.equals(name, eventDto.name) && Objects.equals(invitations, eventDto.invitations) && Objects.equals(owner, eventDto.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, invitations, owner);
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", invitations=" + invitations +
                ", owner=" + owner +
                '}';
    }
}
