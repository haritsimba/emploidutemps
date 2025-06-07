package com.project.telecom.rnu.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Responsable extends User {

    @OneToMany
    private List<Creneau> creneaux = new ArrayList<>();

    public Responsable(String username, String email, String password, String role, List<Creneau> creneaux) {
        super(null, username, email, password, role);
        this.creneaux = creneaux;
    }
}