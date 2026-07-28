package com.gymflow.gymflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String Name;
    private String membershipType;
    private String feeamount="Paid";
    private Double dailyCalorieGoal;
    private Double dailyProteinGoal;
}
