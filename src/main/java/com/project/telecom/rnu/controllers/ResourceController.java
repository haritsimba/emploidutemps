package com.project.telecom.rnu.controllers;

import com.project.telecom.rnu.dtos.ResourceAddDto;
import com.project.telecom.rnu.entities.Resource;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.responses.OperationResponse;
import com.project.telecom.rnu.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ResourceAddDto dto) {
        Resource resource = new Resource();
        resource.setLibelle(dto.getLibelle());
        OperationResponse response = resourceService.createResource(resource);
        return response.getStatus() == OperationResponseStatus.OK ?
                ResponseEntity.ok(response.getResponseData()) :
                ResponseEntity.badRequest().body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(resourceService.getAllResources().getResponseData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        OperationResponse response = resourceService.getResourceById(id);
        return response.getStatus() == OperationResponseStatus.OK ?
                ResponseEntity.ok(response.getResponseData()) :
                ResponseEntity.status(404).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ResourceAddDto dto) {
        Resource resource = new Resource();
        resource.setLibelle(dto.getLibelle());
        OperationResponse response = resourceService.updateResource(id, resource);
        return response.getStatus() == OperationResponseStatus.OK ?
                ResponseEntity.ok(response.getResponseData()) :
                ResponseEntity.status(404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        OperationResponse response = resourceService.deleteResource(id);
        return response.getStatus() == OperationResponseStatus.OK ?
                ResponseEntity.ok("Resource deleted") :
                ResponseEntity.status(404).body(response);
    }
}
