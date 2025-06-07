package com.project.telecom.rnu.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    String nom;
    @ManyToMany
    List<Student> students;
}
