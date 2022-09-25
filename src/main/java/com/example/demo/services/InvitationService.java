package com.example.demo.services;

import com.example.demo.dtos.InvitationDto;
import com.example.demo.models.Invitation;

import java.util.Set;

public interface InvitationService {
    Invitation create(InvitationDto invitationDto);

    Set<Invitation> getAll();

    Invitation getById(Long id);

    Set<Invitation> getByUserId(Long userId);

    Set<Invitation> getByEventId(Long eventId);

    Invitation setStatus(Long id, String status);
}
