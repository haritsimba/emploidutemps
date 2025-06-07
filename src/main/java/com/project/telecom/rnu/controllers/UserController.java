package com.project.telecom.rnu.controllers;

import com.project.telecom.rnu.dtos.UserCreateDto;
import com.project.telecom.rnu.dtos.UserLoginDto;
import com.project.telecom.rnu.enumerations.OperationResponseStatus;
import com.project.telecom.rnu.responses.OperationResponse;
import com.project.telecom.rnu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto dto) {
        OperationResponse response = userService.createUser(dto);
        return response.getStatus() == OperationResponseStatus.OK
                ? ResponseEntity.ok(response.getResponseData())
                : ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto dto) {
        OperationResponse response = userService.login(dto);
        return response.getStatus() == OperationResponseStatus.OK
                ? ResponseEntity.ok(response.getResponseData())
                : ResponseEntity.status(401).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.findAll().getResponseData());
    }
}
