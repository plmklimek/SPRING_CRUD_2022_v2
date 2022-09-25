package com.example.demo.controllers;

import com.example.demo.models.Event;
import com.example.demo.services.impl.EventServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class EventController {
    private final EventServiceImpl eventService;

    @GetMapping("/events")
    public Set<Event> getEvents(){
        return eventService.getAll();
    }

    @GetMapping("/events/{id}")
    public Event getEvent(@PathVariable("id") Long id){
        return eventService.getById(id);
    }
}
