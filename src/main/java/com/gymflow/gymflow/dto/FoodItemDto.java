package com.gymflow.gymflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDto {

    private Long foodId;
    private String foodName;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
}