package com.project.telecom.rnu.repositories;

import com.project.telecom.rnu.entities.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsableRepository extends JpaRepository<Responsable,Long> {
    Responsable findByEmailAndPassword(String email,String password);
}
