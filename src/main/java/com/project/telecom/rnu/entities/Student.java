package com.project.telecom.rnu.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {

    @ManyToMany
    @JsonIgnoreProperties("students")
    private List<Groupe> groupes = new ArrayList<>();

    public Student(String username, String email, String password, String role, List<Groupe> groupes) {
        super(null, username, email, password, role);
        this.groupes = groupes;
    }
}
