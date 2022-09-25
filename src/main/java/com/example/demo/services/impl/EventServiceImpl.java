package com.example.demo.services.impl;

import com.example.demo.dtos.EventDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.mappers.EventMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Event;
import com.example.demo.repositories.EventRepository;
import com.example.demo.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final static String USER_DOESNT_EXIST = "USER DOESNT EXIST";

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
    public Set<Event> getAll() {
        return new HashSet<>(eventRepository.findAll());
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(USER_DOESNT_EXIST));
    }
}
