package com.gymflow.gymflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraningRequestDto {
    @NotBlank(message = "Goal should be required ")
    private String goal;
    @NotBlank(message = "week must be men")
    @Positive(message = "must be in positive format")
    private Double durationWeeks;
    private String trainerPreference;
}
