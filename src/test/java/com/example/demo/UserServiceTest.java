package com.example.demo;

import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Authority;
import com.example.demo.models.Event;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.impl.AuthorityServiceImpl;
import com.example.demo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityServiceImpl authorityService;

    @InjectMocks
    private UserServiceImpl userService;

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

    @Test
    void getByIdWhenExist(){
        doReturn(Optional.of(getUser())).when(userRepository).findById(1L);

        assertDoesNotThrow(() -> {
            userService.getById(1L);
        });
    }

    @Test
    void getByIdWhenDoesntExist(){
        doReturn(Optional.ofNullable(null)).when(userRepository).findById(1L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> userService.getById(1L))
                .withMessage("USER DOESNT EXIST");
    }

    @Test
    void getByEmailWhenExist(){
        doReturn(Optional.of(getUser())).when(userRepository).findById(1L);

        assertDoesNotThrow(() -> {
            userService.getById(1L);
        });
    }

    @Test
    void getByEMailWhenDoesntExist(){
        doReturn(Optional.ofNullable(null)).when(userRepository).findById(1L);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> userService.getById(1L))
                .withMessage("USER DOESNT EXIST");
    }

    @Test
    void register(){
        assertDoesNotThrow(() -> {
            userService.register(UserMapper.mapUserToUserDto(getUser()));
        });
    }
}
