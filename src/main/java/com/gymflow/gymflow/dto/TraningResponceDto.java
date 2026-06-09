package com.gymflow.gymflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraningResponceDto {
    private Long id;
    private String goal;
    private String  durationWeeks;
    private Double price;
    private String assignedTrainer;
}
