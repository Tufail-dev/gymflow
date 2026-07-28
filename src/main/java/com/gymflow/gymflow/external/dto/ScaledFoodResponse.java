package com.gymflow.gymflow.external.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScaledFoodResponse {
    private String foodName;
    private Double grams;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
}