package com.gymflow.gymflow.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3 , max = 20)
    private String Name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format ")

    private String email;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
    private String phone;

    @NotBlank(message = "Membership type required")
    private String membershipType;
    private BigDecimal feeamount;
}
