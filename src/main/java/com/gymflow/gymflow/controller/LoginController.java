package com.gymflow.gymflow.controller;

import com.gymflow.gymflow.dto.LoginRequestDto;

import com.gymflow.gymflow.dto.LoginResponseDto;
import com.gymflow.gymflow.dto.UserResponceDto;
import com.gymflow.gymflow.security.JwtUtil;
import com.gymflow.gymflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UserService userService;
//    @PostMapping("/login")
//     public ResponseEntity<UserResponceDto> userlogin(@RequestBody LoginRequestDto loginRequestDto){
//         UserResponceDto userResponceDto= userService.login(loginRequestDto);
//         return  new ResponseEntity<>(userResponceDto, HttpStatus.OK);
//     }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto
    ){
         UsernamePasswordAuthenticationToken authenticationToken =
                 new UsernamePasswordAuthenticationToken(
                         loginRequestDto.getUsername(),
                         loginRequestDto.getPassword()
                 );

         Authentication authentication =
                 authenticationManager.authenticate(authenticationToken);
         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         String token= jwtUtil.generateTokenFromusername(userDetails);
         return ResponseEntity.ok(new LoginResponseDto(token));
     }

}
