package com.project.telecom.rnu.mappers;

import com.project.telecom.rnu.dtos.CreneauDto;
import com.project.telecom.rnu.entities.Creneau;

public class CreneauxMapper {
    public static CreneauDto mapToDto(Creneau creneau) {
        CreneauDto dto = new CreneauDto();
        dto.setId(creneau.getId());
        dto.setTitle(creneau.getTitle());
        dto.setResponsable(creneau.getResponsable() != null ? creneau.getResponsable().getUsername() : null);
        dto.setGroupe(creneau.getGroupe() != null ? creneau.getGroupe().getNom() : null);
        dto.setStart(creneau.getStart());
        dto.setDescription(creneau.getDescription());
        dto.setEnd(creneau.getEnd());
        dto.setRessourceId(creneau.getResource() != null ? creneau.getResource().getId() : null);
        dto.setResponsableId(creneau.getResponsable() != null ? creneau.getResponsable().getId() : null);
        dto.setGroupeId(creneau.getGroupe() != null ? creneau.getGroupe().getId() : null);
        dto.setRessourceLabel(creneau.getResource() != null ? creneau.getResource().getLibelle() : null);
        return dto;
    }
}
