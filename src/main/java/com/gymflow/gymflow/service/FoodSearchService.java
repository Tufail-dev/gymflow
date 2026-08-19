package com.gymflow.gymflow.service;

import com.gymflow.gymflow.external.dto.FoodSearchResponseDto;
import com.gymflow.gymflow.external.UsdaApiService;
import com.gymflow.gymflow.external.dto.UsdaFoodItem;
import com.gymflow.gymflow.external.dto.UsdaNutrient;
import com.gymflow.gymflow.external.dto.UsdaSearchResponse;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;

@Service
public class FoodSearchService {

    private final UsdaApiService usdaApiService;

    public FoodSearchService(UsdaApiService usdaApiService) {
        this.usdaApiService = usdaApiService;
    }

    public List<FoodSearchResponseDto> searchFoodWithNutrition(String query) {

        // USDA se search response lo
        UsdaSearchResponse usdaSearchResponse =
                usdaApiService.searchFood(query);

        // USDA ki food list
        List<UsdaFoodItem> usdaFoodItems =
                usdaSearchResponse.getFoods();

        // Final response list
        List<FoodSearchResponseDto> result =
                new ArrayList<>();

        // Har food ko process karo
        for (UsdaFoodItem food : usdaFoodItems) {

            Double calories = 0.0;
            Double protein = 0.0;
            Double fat = 0.0;
            Double carbs = 0.0;

            // Is food ke nutrients
            if (food.getFoodNutrients() != null) {

                for (UsdaNutrient nutrient : food.getFoodNutrients()) {

                    String nutrientName =
                            nutrient.getNutrientName();

                    if (nutrientName == null) {
                        continue;
                    }

                    if (nutrientName.equalsIgnoreCase("Energy")) {

                        calories = nutrient.getValue();

                    } else if (
                            nutrientName.equalsIgnoreCase("Protein")
                    ) {

                        protein = nutrient.getValue();

                    } else if (
                            nutrientName.equalsIgnoreCase("Total lipid (fat)")
                    ) {

                        fat = nutrient.getValue();

                    } else if (
                            nutrientName.equalsIgnoreCase(
                                    "Carbohydrate, by difference"
                            )
                    ) {

                        carbs = nutrient.getValue();
                    }
                }
            }

            // DTO create karo
            FoodSearchResponseDto dto =
                    new FoodSearchResponseDto(
                            food.getFdcId(),
                            food.getDescription(),
                            calories,
                            protein,
                            fat,
                            carbs
                    );

            // Result list mein add karo
            result.add(dto);
        }

        return result;
    }
}