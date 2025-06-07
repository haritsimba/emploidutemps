package com.project.telecom.rnu.controllers;

import com.project.telecom.rnu.dtos.CreneauCreateDto;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.responses.OperationResponse;
import com.project.telecom.rnu.services.CreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/creneaux")
@CrossOrigin
public class CreneauController {

    @Autowired
    CreneauService creneauService;

    @PostMapping
    public ResponseEntity<?> createCreneau(@RequestBody CreneauCreateDto dto) {
        OperationResponse serviceResponse = creneauService.createCreneau(dto);
        if (serviceResponse.getStatus() == OperationResponseStatus.OK) {
            return ResponseEntity.ok(serviceResponse.getResponseData());
        }
        return ResponseEntity.status(400).body(serviceResponse);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAllCreneaux() {
        return ResponseEntity.ok(creneauService.getAllCreneaux().getResponseData());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCreneau(@PathVariable Long id,@RequestBody CreneauCreateDto dto){
        OperationResponse response = creneauService.updateCreneau(id, dto);
        if (response.getStatus() == OperationResponseStatus.OK) {
            return ResponseEntity.ok("Creneau deleted successfully.");
        }
        return ResponseEntity.status(400).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCreneauById(@PathVariable Long id) {
        OperationResponse response = creneauService.getCreneauById(id);
        if (response.getStatus() == OperationResponseStatus.OK) {
            return ResponseEntity.ok(response.getResponseData());
        }
        return ResponseEntity.status(404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCreneau(@PathVariable Long id) {
        OperationResponse response = creneauService.deleteCreneau(id);
        if (response.getStatus() == OperationResponseStatus.OK) {
            return ResponseEntity.ok("Creneau deleted successfully.");
        }
        return ResponseEntity.status(404).body(response);
    }
}

