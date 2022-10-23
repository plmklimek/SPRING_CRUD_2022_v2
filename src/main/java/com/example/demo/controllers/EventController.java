package com.example.demo.controllers;

import com.example.demo.services.impl.EventServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {
    private final EventServiceImpl eventService;

    @GetMapping("/events")
    public ResponseEntity getEvents(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(eventService.getAll());
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/events/{id}")
    public ResponseEntity getEvent(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(eventService.getById(id));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }
}
