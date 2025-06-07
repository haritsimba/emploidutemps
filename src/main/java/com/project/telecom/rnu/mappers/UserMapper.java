package com.project.telecom.rnu.mappers;

import com.project.telecom.rnu.dtos.AuthDto;
import com.project.telecom.rnu.dtos.UserDto;
import com.project.telecom.rnu.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDto mapToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setRole(user.getRole());
        dto.setUsername(user.getUsername());
        return dto;
    }

    public static AuthDto mapToAuthDto(User user){
        AuthDto authDto = new AuthDto();
        authDto.setRole(user.getRole());
        authDto.setUsername(user.getUsername());
        authDto.setId(user.getId());
        authDto.setEmail(user.getEmail());
        return authDto;
    }

    public static List<UserDto> mapListToDto(List<User> users){
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user : users){
            userDtos.add(mapToDto(user));
        }
        return userDtos;
    }

}
