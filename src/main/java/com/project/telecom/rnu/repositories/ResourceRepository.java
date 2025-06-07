package com.project.telecom.rnu.repositories;

import com.project.telecom.rnu.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource,Long> {
}
