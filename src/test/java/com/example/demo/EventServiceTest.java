package com.example.demo;

import com.example.demo.mappers.EventMapper;
import com.example.demo.models.Authority;
import com.example.demo.models.Event;
import com.example.demo.models.User;
import com.example.demo.repositories.EventRepository;
import com.example.demo.services.impl.EventServiceImpl;
import com.example.demo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private EventServiceImpl eventService;

    public User getUser(){
        return User.builder()
                .id(1L)
                .email("mail@mail.com")
                .password("test")
                .invitations(new HashSet<>())
                .events(new HashSet<>())
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
                .invitations(new HashSet<>())
                .build();
    }

    @Test
    void createEvent(){
        doReturn(getUser()).when(userService).getByEmail(anyString());
        doReturn(getUser().getEmail()).when(userService).login();
        assertDoesNotThrow(() -> {
            eventService.create(EventMapper.mapEventToEventDto(getEvent()));
        });
    }

    @Test
    void getByIdWhenExist(){
        doReturn(Optional.of(getEvent())).when(eventRepository).findById(1L);

        assertDoesNotThrow(() -> {
            eventService.getById(1L);
        });
    }

    @Test
    void getByIdWhenDoesntExist(){
        doReturn(Optional.ofNullable(null)).when(eventRepository).findById(1L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> eventService.getById(1L))
                .withMessage("EVENT DOESNT EXIST");
    }
}
