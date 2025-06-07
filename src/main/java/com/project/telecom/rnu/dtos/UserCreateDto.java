package com.project.telecom.rnu.dtos;

import lombok.Data;

@Data
public class UserCreateDto {
    private String username;
    private String email;
    private String password;
    private String role;
}
