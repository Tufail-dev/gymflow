package com.gymflow.gymflow.service;

import com.gymflow.gymflow.config.SecurityConfig;
import com.gymflow.gymflow.dto.LoginRequestDto;
import com.gymflow.gymflow.dto.UserRequestDto;
import com.gymflow.gymflow.dto.UserResponceDto;

import com.gymflow.gymflow.entity.Role;
import com.gymflow.gymflow.entity.User;
import com.gymflow.gymflow.exception.ResourceNotFoundException;
import com.gymflow.gymflow.exception.UserAlreadyExistsException;
import com.gymflow.gymflow.repository.RoleRepo;
import com.gymflow.gymflow.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired

    private PasswordEncoder passwordEncoder;


    public UserResponceDto registration(UserRequestDto userRequestDto) {
        Optional<User> user = userRepo.findByusername(userRequestDto.getUsername());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        Optional<User> user1 = userRepo.findByemail(userRequestDto.getEmail());

        if (user1.isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        Optional<Role> memberRole =
                roleRepo.findByRoleNameIgnoreCase("MEMBER");

        if (memberRole.isEmpty()) {
            throw new ResourceNotFoundException("Member role not found");
        }

        Role role = memberRole.get();

        User newuser = modelMapper.map(userRequestDto, User.class);
        newuser.setRole(role);
        newuser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepo.save(newuser);
        UserResponceDto userResponceDto =
                modelMapper.map(newuser, UserResponceDto.class);
        userResponceDto.setMessage("User registered successfully");

        return userResponceDto;
    }

    public UserResponceDto login(LoginRequestDto loginRequestDto){
        UserResponceDto userResponceDto=new UserResponceDto();
        Optional<User> user= userRepo.findByusername(loginRequestDto.getUsername());
        if(user.isEmpty()){
            throw new ResourceNotFoundException("Invalid username and password Try again");

        }

            boolean isvalid;
           isvalid= passwordEncoder.matches(loginRequestDto.getPassword(),
                    user.get().getPassword()
                    );
            if(!isvalid){
                throw new ResourceNotFoundException("Incoorect password");
            }

                UserResponceDto userResponceDto1=modelMapper.map(user.get(),UserResponceDto.class);
                userResponceDto1.setMessage("login successfully");




        return userResponceDto1;
    }
}




