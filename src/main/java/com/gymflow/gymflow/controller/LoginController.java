package com.gymflow.gymflow.controller;

import com.gymflow.gymflow.dto.LoginRequestDto;

import com.gymflow.gymflow.dto.UserResponceDto;
import com.gymflow.gymflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
     public ResponseEntity<UserResponceDto> userlogin(@RequestBody LoginRequestDto loginRequestDto){
         UserResponceDto userResponceDto= userService.login(loginRequestDto);
         return  new ResponseEntity<>(userResponceDto, HttpStatus.OK);
     }

}
