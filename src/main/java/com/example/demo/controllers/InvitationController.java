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
    public Event createEvent(@RequestBody EventDto eventDto){
        return invitationService.createEventAndAddOwner(eventDto);
    }

    @GetMapping("/invitations")
    public List<Invitation> getInvitations(){
        return invitationService.getAll();
    }

    @GetMapping("/invitations/{id}")
    public Invitation getInvitationsById(@PathVariable("id") Long id){
        return invitationService.getById(id);
    }

    @GetMapping("/user/{id}/invitations")
    public List<Invitation> getInvitationsByUser(@PathVariable("id") Long id){
        return invitationService.getByUserId(id);
    }

    @GetMapping("/event/{id}/invitations")
    public List<Invitation> getInvitationsByEvent(@PathVariable("id") Long id){
        return invitationService.getByEventId(id);
    }

    @PostMapping("/invitations/{id}")
    public Invitation setStatus(@PathVariable("id") Long id, @PathParam("status") String status){
        return invitationService.setStatus(id, status);
    }
}
