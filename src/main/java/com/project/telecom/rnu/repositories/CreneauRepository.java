package com.project.telecom.rnu.repositories;

import com.project.telecom.rnu.entities.Creneau;
import com.project.telecom.rnu.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreneauRepository extends JpaRepository<Creneau,Long> {
    @Modifying
    @Query("DELETE FROM Creneau c WHERE c.groupe = :groupe")
    void deleteAllByGroupe(@Param("groupe") Groupe groupe);

    // OR if you prefer to just unlink them:
    @Modifying
    @Query("UPDATE Creneau c SET c.groupe = null WHERE c.groupe = :groupe")
    void setGroupeToNullForCreneaux(@Param("groupe") Groupe groupe);
}
