package com.example.demo;

import com.example.demo.mappers.EventMapper;
import com.example.demo.models.Authority;
import com.example.demo.models.Event;
import com.example.demo.models.Invitation;
import com.example.demo.models.StatusInvitation;
import com.example.demo.models.User;
import com.example.demo.repositories.InvitationRepository;
import com.example.demo.services.impl.EventServiceImpl;
import com.example.demo.services.impl.InvitationServiceImpl;
import com.example.demo.services.impl.UserServiceImpl;
import com.example.demo.utills.LoggedUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvitationServiceTest {
    @Mock
    private InvitationRepository invitationRepository;

    @Mock
    private EventServiceImpl eventService;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private InvitationServiceImpl invitationServiceImpl;

    @InjectMocks
    private InvitationServiceImpl invitationService;


    public User getUser(){
        return User.builder()
                .id(1L)
                .email("mail@mail.com")
                .password("test")
                .invitations(new ArrayList<>())
                .events(new ArrayList<>())
                .build();
    }

    public Authority getAuthority(){
        return Authority.builder()
                .id(1L)
                .user(getUser())
                .authority("USER")
                .build();
    }

    public Event getEvent(){
        return Event.builder()
                .id(1L)
                .name("test")
                .owner(getUser())
                .invitations(new ArrayList<>())
                .build();
    }

    public Invitation getInvitation(){
        return Invitation.builder()
                .id(1L)
                .user(getUser())
                .event(getEvent())
                .status(StatusInvitation.PENDING)
                .build();
    }
    @Test
    void getByIdWhenExist(){
        doReturn(Optional.of(getInvitation())).when(invitationRepository).findById(1L);

        assertDoesNotThrow(() -> {
            invitationService.getById(1L);
        });
    }

    @Test
    void getByIdWhenDoesntExist(){
        doReturn(Optional.ofNullable(null)).when(invitationRepository).findById(1L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> invitationService.getById(1L))
                .withMessage("INVITATION DOESNT EXIST");
    }

    @Test
    void getByEventWhenExist(){
        assertDoesNotThrow(() -> {
            invitationService.getByEventId(1L);
        });
    }

    @Test
    void getByUserWhenExist(){
        assertDoesNotThrow(() -> {
            invitationService.getByUserId(1L);
        });
    }

    @Test
    void createEventAndAddOwner(){
        doReturn(getUser().getEmail()).when(userService).login();
        doReturn(getUser()).when(userService).getByEmail(anyString());
        doReturn(getUser()).when(userService).getById(anyLong());
        User user = getUser();
        user.setEvents(List.of(getEvent()));
        getEvent().setOwner(user);
        doReturn(getEvent()).when(eventService).create(EventMapper.mapEventToEventDto(getEvent()));
        doReturn(getEvent()).when(eventService).getById(1L);
        UserDetails applicationUser = mock(UserDetails.class);
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(applicationUser);
        when(LoggedUser.getLoggedUser()).thenReturn(getUser().getEmail());
        assertDoesNotThrow(() -> {
            invitationService.createEventAndAddOwner(EventMapper.mapEventToEventDto(getEvent()));
        });
    }

    @Test
    void create(){
        doReturn(getUser()).when(userService).getById(1L);
        doReturn(getEvent()).when(eventService).getById(1L);
        UserDetails applicationUser = mock(UserDetails.class);
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(applicationUser);
        when(LoggedUser.getLoggedUser()).thenReturn(getUser().getEmail());
        User user = getUser();
        user.setEvents(List.of(getEvent()));
        getEvent().setOwner(user);

        assertDoesNotThrow(() -> {
            invitationService.create(getUser().getId(), getEvent().getId(), false);
        });
    }
}
