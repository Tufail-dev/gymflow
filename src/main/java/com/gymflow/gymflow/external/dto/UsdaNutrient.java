package com.gymflow.gymflow.external.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsdaNutrient {
    private String nutrientName;
    private Double value;
    private String unitName;
    private String quantityInGrams;
}
