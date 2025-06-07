package com.project.telecom.rnu.services;

import com.project.telecom.rnu.dtos.UserCreateDto;
import com.project.telecom.rnu.dtos.UserLoginDto;
import com.project.telecom.rnu.entities.Responsable;
import com.project.telecom.rnu.entities.Student;
import com.project.telecom.rnu.entities.User;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.mappers.UserMapper;
import com.project.telecom.rnu.repositories.ResponsableRepository;
import com.project.telecom.rnu.repositories.StudentRepository;
import com.project.telecom.rnu.repositories.UserRepository;
import com.project.telecom.rnu.responses.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private UserRepository userRepository;

    public OperationResponse createUser(UserCreateDto dto) {
        if ("STUDENT".equalsIgnoreCase(dto.getRole())) {
            Student student = new Student(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getRole(), new ArrayList<>());
            return new OperationResponse(OperationResponseStatus.OK, studentRepository.save(student), "Student created");
        } else if ("RESPONSABLE".equalsIgnoreCase(dto.getRole())) {
            Responsable responsable = new Responsable(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getRole(), new ArrayList<>());
            return new OperationResponse(OperationResponseStatus.OK, UserMapper.mapToAuthDto(responsableRepository.save(responsable)), "Responsable created");
        } else {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Invalid role");
        }
    }

    public OperationResponse login(UserLoginDto dto) {
        User user = studentRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (user == null) {
            user = responsableRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        }

        if (user == null) {
            return new OperationResponse(OperationResponseStatus.USER_ERROR, null, "Invalid credentials");
        }

        return new OperationResponse(OperationResponseStatus.OK, UserMapper.mapToAuthDto(user), "Login successful");
    }

    public OperationResponse findAll(){
        return new OperationResponse(OperationResponseStatus.OK, UserMapper.mapListToDto(userRepository.findAll()),"User list");
    }
}
