package com.example.demo.mappers;

import com.example.demo.dtos.UserDto;
import com.example.demo.models.User;
import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static User mapUserDtoToUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto mapUserToUserDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    public static Set<UserDto> mapUsersToUsersDto(Set<User> users) {
        return users.stream().map(UserMapper::mapUserToUserDto).collect(Collectors.toSet());
    }

    public static Set<User> mapUsersDtoToUsers(Set<UserDto> userDtos) {
        return userDtos.stream().map(UserMapper::mapUserDtoToUser).collect(Collectors.toSet());
    }
}
