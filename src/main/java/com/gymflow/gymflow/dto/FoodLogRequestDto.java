package com.gymflow.gymflow.dto;

import com.gymflow.gymflow.entity.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodLogRequestDto {
    private Long foodId;
    private Double quantity;
    private MealType mealType;
    private Long memberId;

}
