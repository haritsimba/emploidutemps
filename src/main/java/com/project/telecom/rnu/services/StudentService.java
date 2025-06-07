package com.project.telecom.rnu.services;

import com.project.telecom.rnu.entities.Student;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.repositories.StudentRepository;
import com.project.telecom.rnu.responses.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public OperationResponse create(Student student) {
        return new OperationResponse(OperationResponseStatus.OK, studentRepository.save(student), "Student created");
    }

    public OperationResponse getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(value -> new OperationResponse(OperationResponseStatus.OK, value, "Found"))
                .orElseGet(() -> new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Not found"));
    }

    public OperationResponse getAll() {
        List<Student> list = studentRepository.findAll();
        return new OperationResponse(OperationResponseStatus.OK, list, "All students");
    }

    public OperationResponse update(Long id, Student updated) {
        Optional<Student> opt = studentRepository.findById(id);
        if (opt.isEmpty()) return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Not found");

        Student existing = opt.get();
        existing.setUsername(updated.getUsername());
        existing.setEmail(updated.getEmail());
        existing.setPassword(updated.getPassword());
        return new OperationResponse(OperationResponseStatus.OK, studentRepository.save(existing), "Updated");
    }

    public OperationResponse delete(Long id) {
        if (!studentRepository.existsById(id))
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Not found");

        studentRepository.deleteById(id);
        return new OperationResponse(OperationResponseStatus.OK, null, "Deleted");
    }
}
