package com.example.demo.services;

import com.example.demo.dtos.EventDto;
import com.example.demo.models.Event;
import com.example.demo.models.Invitation;

import java.util.List;

public interface InvitationService {
    Event createEventAndAddOwner(EventDto eventDto);

    Invitation create(Long userId, Long eventId, boolean isOwner);

    List<Invitation> getAll();

    Invitation getById(Long id);

    List<Invitation> getByUserId(Long userId);

    List<Invitation> getByEventId(Long eventId);

    Invitation setStatus(Long id, String status);
}
