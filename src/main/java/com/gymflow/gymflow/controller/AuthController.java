package com.gymflow.gymflow.controller;

import com.gymflow.gymflow.dto.UserRequestDto;
import com.gymflow.gymflow.dto.UserResponceDto;
import com.gymflow.gymflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserResponceDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto){
        UserResponceDto userResponceDto= userService.registration(userRequestDto);

        return  new ResponseEntity<>(userResponceDto,HttpStatus.CREATED);
    }

}
