package com.gymflow.gymflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDto {
    private String foodName;
    private double quantityInGrams;
    private String unit;

}
