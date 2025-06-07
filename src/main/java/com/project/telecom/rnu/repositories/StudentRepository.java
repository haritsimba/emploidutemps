package com.project.telecom.rnu.repositories;

import com.project.telecom.rnu.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByEmailAndPassword(String email,String password);
}
