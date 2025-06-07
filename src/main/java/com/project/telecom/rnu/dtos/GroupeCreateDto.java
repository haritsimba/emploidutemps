package com.project.telecom.rnu.dtos;

import lombok.Data;

@Data
public class GroupeCreateDto {
    Long[] studentIds;
    String nom;
}
