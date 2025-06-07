package com.project.telecom.rnu.repositories;

import com.project.telecom.rnu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
