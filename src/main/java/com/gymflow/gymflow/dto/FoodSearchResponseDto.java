package com.gymflow.gymflow.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FoodSearchResponseDto {
    private Long foodId;
    private String foodName;
}
