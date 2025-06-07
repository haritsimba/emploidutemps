package com.project.telecom.rnu.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreneauCreateDto {
    String title;
    LocalDateTime start;
    LocalDateTime end;
    Long ressourceId;
    Long groupId;
    Long responsableId;
    String description;
}
