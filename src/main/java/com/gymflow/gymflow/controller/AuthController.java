package com.gymflow.gymflow.controller;
import com.gymflow.gymflow.dto.UserRequestDto;
import com.gymflow.gymflow.dto.UserResponceDto;
import com.gymflow.gymflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    @GetMapping("/{username}")
    public ResponseEntity<UserResponceDto> getusername(@PathVariable String username){
        UserResponceDto userResponceDto= userService.getUserByUsername(username);
        return new ResponseEntity<>(userResponceDto, HttpStatus.OK);

    }
    @GetMapping("/users")
    public List<UserResponceDto> getAllUsers(){
        List<UserResponceDto> userResponceDto= userService.getAllusers();
       return userResponceDto;

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponceDto> deletuser(@PathVariable Long id){
        UserResponceDto userResponceDto= userService.deleteuser(id);
        return new ResponseEntity<>(userResponceDto, HttpStatus.OK);

    }



}
