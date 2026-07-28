package com.gymflow.gymflow.dto;

import com.gymflow.gymflow.entity.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodLogResponseDto {
    private Long id;

    private String foodName;

    private Double quantity;

    private Double calories;

    private Double protein;

    private Double carbs;

    private Double fat;

    private MealType mealType;

    private LocalDateTime consumedAt;

}
