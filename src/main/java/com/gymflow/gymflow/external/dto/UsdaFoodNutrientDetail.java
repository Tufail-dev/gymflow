package com.gymflow.gymflow.external.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsdaFoodNutrientDetail {
    private UsdaNutrientInfo nutrient;
    private Double amount;
}