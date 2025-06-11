package com.project.telecom.rnu.services;

import com.project.telecom.rnu.entities.Resource;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.mappers.ResourceMapper;
import com.project.telecom.rnu.repositories.CreneauRepository;
import com.project.telecom.rnu.repositories.ResourceRepository;
import com.project.telecom.rnu.responses.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    CreneauRepository creneauRepository;

    public OperationResponse createResource(Resource resource) {
        Resource saved = resourceRepository.save(resource);
        return new OperationResponse(OperationResponseStatus.OK, ResourceMapper.mapToDto(saved), "Resource created successfully");
    }

    public OperationResponse getResourceById(Long id) {
        Optional<Resource> optional = resourceRepository.findById(id);
        return optional.map(resource -> new OperationResponse(OperationResponseStatus.OK, ResourceMapper.mapToDto(resource), "Resource found")).orElseGet(() -> new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Resource not found"));
    }

    public OperationResponse getAllResources() {
        List<Resource> all = resourceRepository.findAll();
        return new OperationResponse(OperationResponseStatus.OK, ResourceMapper.mapListToDto(all), "All resources retrieved");
    }

    public OperationResponse updateResource(Long id, Resource newData) {
        Optional<Resource> optional = resourceRepository.findById(id);
        if (optional.isEmpty()) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Resource not found");
        }

        Resource existing = optional.get();
        existing.setLibelle(newData.getLibelle());

        Resource updated = resourceRepository.save(existing);
        return new OperationResponse(OperationResponseStatus.OK, ResourceMapper.mapToDto(updated), "Resource updated successfully");
    }

    public OperationResponse deleteResource(Long id) {
        if (!resourceRepository.existsById(id)) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Resource not found");
        }

        resourceRepository.deleteById(id);
        return new OperationResponse(OperationResponseStatus.OK, null, "Resource deleted successfully");
    }
}
