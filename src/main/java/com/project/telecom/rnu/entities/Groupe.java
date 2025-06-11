package com.project.telecom.rnu.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    @ManyToMany(mappedBy = "groupes")
    List<Student> students = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    List<Creneau> creneaux = new ArrayList<>();
}
