package com.example.demo.controllers;

import com.example.demo.dtos.EventDto;
import com.example.demo.mappers.InvitationMapper;
import com.example.demo.services.impl.InvitationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InvitationController {
    private final InvitationServiceImpl invitationService;

    @PostMapping("/invitations")
    public ResponseEntity create(@RequestBody Map<String, Long> json){
        Map<String, String> status = new HashMap<>();
        try{
            status.put("status", "Invitation added id:" + invitationService.create(json.get("user_id"), json.get("event_id"), false).getId());
            return new ResponseEntity<>(
                    status,
                    HttpStatus.OK);
        }
        catch (Exception exception){
            status.put("status", exception.getMessage());
            return new ResponseEntity<>(
                    status,
                    HttpStatus.OK);
        }
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
            return ResponseEntity.status(HttpStatus.OK).body(InvitationMapper.mapInvitationsToInvitationsDto(invitationService.getAll()));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/invitations/{id}")
    public ResponseEntity getInvitationsById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(InvitationMapper.mapInvitationToInvitationDto(invitationService.getById(id)));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/user/{id}/invitations")
    public ResponseEntity getInvitationsByUser(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(InvitationMapper.mapInvitationsToInvitationsDto(invitationService.getByUserId(id)));
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
        }
    }

    @GetMapping("/event/{id}/invitations")
    public ResponseEntity getInvitationsByEvent(@PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(InvitationMapper.mapInvitationsToInvitationsDto(invitationService.getByEventId(id)));
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
