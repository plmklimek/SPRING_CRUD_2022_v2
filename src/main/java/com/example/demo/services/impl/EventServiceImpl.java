package com.example.demo.services.impl;

import com.example.demo.dtos.EventDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.mappers.EventMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Event;
import com.example.demo.models.Invitation;
import com.example.demo.models.User;
import com.example.demo.repositories.EventRepository;
import com.example.demo.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final static String EVENT_DOESNT_EXIST = "EVENT DOESNT EXIST";

    private final EventRepository eventRepository;

    private final UserServiceImpl userService;

    @Override
    public Event create(EventDto eventDto) {
        UserDto owner = UserMapper.mapUserToUserDto(userService.getByEmail(userService.login()));
        eventDto.setOwner(owner);
        Event save = eventRepository.save(EventMapper.mapEventDtoToEvent(eventDto));
        return save;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(EVENT_DOESNT_EXIST));
    }

    public Set<User> getAvailableForEvent(Long eventId) {
        Event eventById = getById(eventId);
        Set<User> usersInEvent = eventById.getInvitations().stream()
                .map(Invitation::getUser)
                .collect(Collectors.toSet());
        return userService.getAll().stream().filter(user -> !usersInEvent.contains(user)).collect(Collectors.toSet());
    }
}
