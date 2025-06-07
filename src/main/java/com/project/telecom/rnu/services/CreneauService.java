package com.project.telecom.rnu.services;

import com.project.telecom.rnu.dtos.CreneauCreateDto;
import com.project.telecom.rnu.dtos.CreneauDto;
import com.project.telecom.rnu.entities.Creneau;
import com.project.telecom.rnu.entities.Groupe;
import com.project.telecom.rnu.entities.Resource;
import com.project.telecom.rnu.entities.Responsable;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.mappers.CreneauxMapper;
import com.project.telecom.rnu.repositories.CreneauRepository;
import com.project.telecom.rnu.repositories.GroupeRepository;
import com.project.telecom.rnu.repositories.ResourceRepository;
import com.project.telecom.rnu.repositories.ResponsableRepository;
import com.project.telecom.rnu.responses.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreneauService {

    @Autowired
    private CreneauRepository creneauRepository;
    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ResponsableRepository responsableRepository;

    public OperationResponse createCreneau(CreneauCreateDto dtoIn) {
        if (dtoIn.getGroupId() != null && groupeRepository.findById(dtoIn.getGroupId()).isEmpty()) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Group not found");
        }
        if (dtoIn.getResponsableId() != null && responsableRepository.findById(dtoIn.getResponsableId()).isEmpty()) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Responsable not found");
        }
        if (dtoIn.getRessourceId() == null || resourceRepository.findById(dtoIn.getRessourceId()).isEmpty()) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Resource not found");
        }

        Responsable responsable = dtoIn.getResponsableId() != null ? responsableRepository.findById(dtoIn.getResponsableId()).orElse(null) : null;
        Groupe groupe = dtoIn.getGroupId() != null ? groupeRepository.findById(dtoIn.getGroupId()).orElse(null) : null;
        Resource resource = resourceRepository.findById(dtoIn.getRessourceId()).get();
        Creneau creneau = new Creneau();
        creneau.setDescription(dtoIn.getDescription());
        creneau.setStart(dtoIn.getStart());
        creneau.setResource(resource);
        creneau.setEnd(dtoIn.getEnd());
        creneau.setTitle(dtoIn.getTitle());
        creneau.setResponsable(responsable);
        creneau.setGroupe(groupe);

        Creneau savedCreneau = creneauRepository.save(creneau);
        CreneauDto dtoOut = CreneauxMapper.mapToDto(savedCreneau);

        return new OperationResponse(OperationResponseStatus.OK, dtoOut, "Creneau created successfully");
    }

    public OperationResponse getCreneauById(Long id) {
        Optional<Creneau> optionalCreneau = creneauRepository.findById(id);
        if (optionalCreneau.isEmpty()) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Creneau not found");
        }
        CreneauDto dto = CreneauxMapper.mapToDto(optionalCreneau.get());
        return new OperationResponse(OperationResponseStatus.OK, dto, "Creneau found");
    }

    public OperationResponse getAllCreneaux() {
        List<Creneau> creneaux = creneauRepository.findAll();
        List<CreneauDto> dtoList = creneaux.stream().map(CreneauxMapper::mapToDto).collect(Collectors.toList());
        return new OperationResponse(OperationResponseStatus.OK, dtoList, "All creneaux retrieved");
    }

    public OperationResponse updateCreneau(Long id, CreneauCreateDto dtoIn) {
        Optional<Creneau> optionalCreneau = creneauRepository.findById(id);
        if (optionalCreneau.isEmpty()) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Creneau not found");
        }

        Creneau creneau = optionalCreneau.get();
        creneau.setTitle(dtoIn.getTitle());
        creneau.setDescription(dtoIn.getDescription());
        creneau.setStart(dtoIn.getStart());
        creneau.setEnd(dtoIn.getEnd());

        if (dtoIn.getGroupId() != null) {
            groupeRepository.findById(dtoIn.getGroupId()).ifPresent(creneau::setGroupe);
        }

        if (dtoIn.getResponsableId() != null) {
            responsableRepository.findById(dtoIn.getResponsableId()).ifPresent(creneau::setResponsable);
        }

        Creneau updated = creneauRepository.save(creneau);
        CreneauDto dtoOut = CreneauxMapper.mapToDto(updated);

        return new OperationResponse(OperationResponseStatus.OK, dtoOut, "Creneau updated successfully");
    }

    public OperationResponse deleteCreneau(Long id) {
        if (!creneauRepository.existsById(id)) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Creneau not found");
        }
        creneauRepository.deleteById(id);
        return new OperationResponse(OperationResponseStatus.OK, null, "Creneau deleted successfully");
    }
}
