package com.project.telecom.rnu.dtos;

import lombok.Data;

@Data
public class AuthDto {
    String username;
    String email;
    Long id;
    String role;
}
