package com.project.telecom.rnu.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Creneau {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @NotNull
    String title;
    String description;
    @ManyToOne
    Resource resource;
    @ManyToOne
    Responsable responsable;
    @ManyToOne
    Groupe groupe;
    @NotNull
    LocalDateTime start;
    @NotNull
    LocalDateTime end;

}
