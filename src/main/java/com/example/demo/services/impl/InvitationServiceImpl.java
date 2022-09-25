package com.example.demo.services.impl;

import com.example.demo.dtos.InvitationDto;
import com.example.demo.mappers.InvitationMapper;
import com.example.demo.models.Invitation;
import com.example.demo.models.StatusInvitation;
import com.example.demo.repositories.InvitationRepository;
import com.example.demo.services.InvitationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class InvitationServiceImpl implements InvitationService {
    private static final String INVITATION_DOESNT_EXIST = "INVITATION DOESNT EXIST";

    private final InvitationRepository invitationRepository;

    @Override
    public Invitation create(InvitationDto invitationDto) {
        return invitationRepository.save(InvitationMapper.mapInvitationDtoToInvitation(invitationDto));
    }

    @Override
    public Set<Invitation> getAll() {
        return new HashSet<>(invitationRepository.findAll());
    }

    @Override
    public Invitation getById(Long id) {
        return invitationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(INVITATION_DOESNT_EXIST));
    }

    @Override
    public Set<Invitation> getByUserId(Long userId) {
        return getAll().stream().filter(invitation -> userId.equals(invitation.getUser().getId())).collect(Collectors.toSet());
    }

    @Override
    public Set<Invitation> getByEventId(Long eventId) {
        return getAll().stream().filter(invitation -> eventId.equals(invitation.getEvent().getId())).collect(Collectors.toSet());
    }

    @Override
    public Invitation setStatus(Long id, String status) {
        Invitation invitationById = getById(id);
        invitationById.setStatus(StatusInvitation.valueOf(status));
        return invitationRepository.save(invitationById);
    }
}
