package com.example.demo.services.impl;

import com.example.demo.dtos.EventDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Event;
import com.example.demo.models.Invitation;
import com.example.demo.models.StatusInvitation;
import com.example.demo.models.User;
import com.example.demo.repositories.InvitationRepository;
import com.example.demo.services.InvitationService;
import com.example.demo.utills.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class InvitationServiceImpl implements InvitationService {
    private static final String INVITATION_DOESNT_EXIST = "INVITATION DOESNT EXIST";

    private final InvitationRepository invitationRepository;

    private final UserServiceImpl userService;

    private final EventServiceImpl eventService;

    @Override
    public Event createEventAndAddOwner(EventDto eventDto) {
        UserDto owner = UserMapper.mapUserToUserDto(userService.getByEmail(userService.login()));
        eventDto.setOwner(owner);
        Event save = eventService.create(eventDto);
        create(owner.getId(), save.getId(), true);
        return save;
    }

    @Override
    public Invitation create(Long userId, Long eventId, boolean isOwner) {
        User userById = userService.getById(userId);
        Event eventById = eventService.getById(eventId);
        StatusInvitation statusInvitation;
        Set<Invitation> invitationExist = eventById.getInvitations().stream()
                .filter(invitation -> invitation.getUser().getId().equals(userId)).collect(Collectors.toSet());
        String loggedUser = LoggedUser.getLoggedUser();
        if(invitationExist.size() == 0 && (loggedUser.equals(userById.getEmail()) || loggedUser.equals(eventById.getOwner().getEmail()))){
            if(isOwner){
                statusInvitation = StatusInvitation.ACCEPTED;
            }
            else{
                statusInvitation = StatusInvitation.PENDING;
            }
            Invitation invitation = Invitation.builder()
                    .user(userById)
                    .event(eventById)
                    .status(statusInvitation)
                    .build();
            return invitationRepository.save(invitation);
        }
        throw new IllegalArgumentException("Invitation exists");
    }

    @Override
    public List<Invitation> getAll() {
        return invitationRepository.findAll();
    }

    @Override
    public Invitation getById(Long id) {
        return invitationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(INVITATION_DOESNT_EXIST));
    }

    @Override
    public List<Invitation> getByUserId(Long userId) {
        return getAll().stream().filter(invitation -> userId.equals(invitation.getUser().getId())).collect(Collectors.toList());
    }

    @Override
    public List<Invitation> getByEventId(Long eventId) {
        return getAll().stream().filter(invitation -> eventId.equals(invitation.getEvent().getId())).collect(Collectors.toList());
    }

    @Override
    public Invitation setStatus(Long id, String status) {
        String loggedUser = LoggedUser.getLoggedUser();
        Invitation invitationById = getById(id);
        invitationById.setStatus(StatusInvitation.valueOf(status));
        if(loggedUser.equals(invitationById.getUser().getEmail())){
            return invitationRepository.save(invitationById);
        }
        throw new IllegalArgumentException("Invalid user");
    }
}
