package com.example.demo.services;

import com.example.demo.dtos.EventDto;
import com.example.demo.models.Event;

import java.util.List;
import java.util.Set;

public interface EventService{
    Event create(EventDto eventDto);

    List<Event> getAll();

    Event getById(Long id);
}
