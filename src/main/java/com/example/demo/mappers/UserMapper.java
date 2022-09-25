package com.example.demo.mappers;

import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;

public class UserMapper {
    public static User mapUserDtoToUser(UserDto userDto) {
        if(userDto == null){
            return null;
        }

        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .authorities(userDto.getAuthorities())
                .invitations(InvitationMapper.mapInvitationsDtoToInvitations(userDto.getInvitations()))
                .build();
    }

    public static UserDto mapUserToUserDto(User user) {
        if(user == null){
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .invitations(InvitationMapper.mapInvitationsToInvitationsDto(user.getInvitations()))
                .build();
    }

    public static User mapUserDtoToUserWithoutRelations(UserDto userDto) {
        if(userDto == null){
            return null;
        }

        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .authorities(userDto.getAuthorities())
                .build();
    }

    public static UserDto mapUserToUserDtoWithoutRelations(User user) {
        if(user == null){
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .build();
    }
}
