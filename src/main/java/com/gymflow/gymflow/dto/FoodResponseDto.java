package com.gymflow.gymflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponseDto {
    private String foodName;
    private double calorie;
    private double protien;
    private double fat;
    private double carbs;
    private double quantityInGrams;

}
