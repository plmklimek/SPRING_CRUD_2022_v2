package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDto {
    private Long id;

    private String name;

    private List<InvitationDto> invitations = new ArrayList<>();

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
