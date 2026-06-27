package com.gymflow.gymflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDto {
    @NotBlank
    @Size(min=3,message = "name feild should contain at least 3 chracter")
    private String username;
    @NotBlank
   @Email
    private String email;
    @NotBlank
    @Size(min=8,message = "Password feild should contain at least 8 chracter")
    private String password;

}
