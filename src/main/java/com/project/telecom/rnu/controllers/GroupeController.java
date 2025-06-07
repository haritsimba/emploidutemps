package com.project.telecom.rnu.controllers;

import com.project.telecom.rnu.dtos.GroupeCreateDto;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.responses.OperationResponse;
import com.project.telecom.rnu.services.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groupes")
@CrossOrigin
public class GroupeController {

    @Autowired
    private GroupeService groupeService;

    @PostMapping
    public ResponseEntity<?> createGroupe(@RequestBody GroupeCreateDto dto) {
        OperationResponse response = groupeService.create(dto);
        return response.getStatus() == OperationResponseStatus.OK ?
                ResponseEntity.ok(response.getResponseData()) :
                ResponseEntity.badRequest().body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllGroupes() {
        return ResponseEntity.ok(groupeService.getAll().getResponseData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupeById(@PathVariable Long id) {
        OperationResponse response = groupeService.getById(id);
        return response.getStatus() == OperationResponseStatus.OK ?
                ResponseEntity.ok(response.getResponseData()) :
                ResponseEntity.status(404).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroupe(@PathVariable Long id, @RequestBody GroupeCreateDto dto) {
        OperationResponse response = groupeService.update(id, dto);
        return response.getStatus() == OperationResponseStatus.OK ?
                ResponseEntity.ok(response.getResponseData()) :
                ResponseEntity.status(404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupe(@PathVariable Long id) {
        OperationResponse response = groupeService.delete(id);
        return response.getStatus() == OperationResponseStatus.OK ?
                ResponseEntity.ok("Groupe deleted") :
                ResponseEntity.status(404).body(response);
    }
}
