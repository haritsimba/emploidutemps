package com.project.telecom.rnu.controllers;

import com.project.telecom.rnu.dtos.StudentCreateDto;
import com.project.telecom.rnu.entities.Student;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.responses.OperationResponse;
import com.project.telecom.rnu.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StudentCreateDto dto) {
        Student student = new Student();
        student.setEmail(dto.getEmail());
        student.setUsername(dto.getUsername());
        student.setPassword(dto.getPassword());
        student.setRole("STUDENT");
        OperationResponse response = studentService.create(student);
        return ResponseEntity.ok(response.getResponseData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        OperationResponse response = studentService.getById(id);
        return response.getStatus() == OperationResponseStatus.OK
                ? ResponseEntity.ok(response.getResponseData())
                : ResponseEntity.status(404).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(studentService.getAll().getResponseData());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StudentCreateDto dto) {
        Student student = new Student();
        student.setEmail(dto.getEmail());
        student.setUsername(dto.getUsername());
        student.setPassword(dto.getPassword());
        student.setRole("STUDENT");
        OperationResponse response = studentService.update(id, student);
        return response.getStatus() == OperationResponseStatus.OK
                ? ResponseEntity.ok(response.getResponseData())
                : ResponseEntity.status(404).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        OperationResponse response = studentService.delete(id);
        return response.getStatus() == OperationResponseStatus.OK
                ? ResponseEntity.ok("Deleted successfully")
                : ResponseEntity.status(404).body(response);
    }
}
