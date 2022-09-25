package com.example.demo.controllers;

import com.example.demo.dtos.EventDto;
import com.example.demo.models.Event;
import com.example.demo.models.Invitation;
import com.example.demo.services.impl.InvitationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
public class InvitationController {
    private final InvitationServiceImpl invitationService;

    @PostMapping("/invitations")
    public ResponseEntity<String> create(@RequestBody Map<String, Long> json){
        return new ResponseEntity<>(
                "Invitation added id:" + invitationService.create(json.get("user_id"), json.get("event_id"), false).getId(),
                HttpStatus.OK);
    }

    @PostMapping("/events")
    public ResponseEntity createEvent(@RequestBody EventDto eventDto){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(invitationService.createEventAndAddOwner(eventDto));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/invitations")
    public ResponseEntity getInvitations(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(invitationService.getAll());
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/invitations/{id}")
    public ResponseEntity getInvitationsById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(invitationService.getById(id));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/user/{id}/invitations")
    public ResponseEntity getInvitationsByUser(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(invitationService.getByUserId(id));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/event/{id}/invitations")
    public ResponseEntity getInvitationsByEvent(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(invitationService.getByEventId(id));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @PostMapping("/invitations/{id}")
    public ResponseEntity setStatus(@PathVariable("id") Long id, @PathParam("status") String status){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(invitationService.setStatus(id, status));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }
}
