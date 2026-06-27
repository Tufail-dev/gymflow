package com.gymflow.gymflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponceDto {

    private Long id;
    private String username;
    private String email;
    private String role;
    private String message;

}
