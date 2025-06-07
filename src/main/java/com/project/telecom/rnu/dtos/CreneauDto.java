package com.project.telecom.rnu.dtos;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreneauDto {
    Long id;
    String title;
    LocalDateTime start;
    LocalDateTime end;
    Long ressourceId;
    String ressourceLabel;
    String groupe;
    Long groupeId;
    String responsable;
    String description;
    Long responsableId;
}
