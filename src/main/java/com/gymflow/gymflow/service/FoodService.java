package com.gymflow.gymflow.service;

import com.gymflow.gymflow.dto.FoodItemDto;
import com.gymflow.gymflow.dto.FoodSearchResponseDto;
import com.gymflow.gymflow.external.UsdaApiService;
import com.gymflow.gymflow.external.dto.UsdaFoodItem;

import com.gymflow.gymflow.external.dto.UsdaSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    private UsdaApiService usdaApiService;

    // Step 1: Search foods
    public List<FoodItemDto> searchFood(String query) {

        UsdaSearchResponse response = usdaApiService.searchFood(query);

        if (response == null || response.getFoods() == null) {
            return Collections.emptyList();
        }

        List<UsdaFoodItem> foods = response.getFoods();

        List<FoodItemDto> searchResults = new ArrayList<>();

        for (UsdaFoodItem item : foods) {

           FoodItemDto dto = new FoodItemDto();

            dto.setFoodId(item.getFdcId());
            dto.setFoodName(item.getDescription());

            searchResults.add(dto);
        }

        return searchResults;
    }

    // Step 2: Get nutrition for the selected food
//    public FoodResponseDto getFoodDetails(Long foodId, double quantityInGrams) {
//
//        UsdaFoodItem foodItem = usdaApiService.getFoodDetails(foodId);
//
//        if (foodItem == null) {
//            throw new RuntimeException("Food not found for id: " + foodId);
//        }
//
//        double caloriesPer100g = 0;
//        double proteinPer100g = 0;
//        double carbsPer100g = 0;
//        double fatPer100g = 0;
//        if()

//        if (foodItem.getFoodNutrients() != null) {
//            for (UsdaNutrient nutrient : foodItem.getFoodNutrients()) {
//                String name = nutrient.getNutrientName();
//                if (name == null) continue;
//
//                switch (name) {
//                    case "Energy" -> caloriesPer100g = nutrient.getValue();
//                    case "Protein" -> proteinPer100g = nutrient.getValue();
//                    case "Carbohydrate, by difference" -> carbsPer100g = nutrient.getValue();
//                    case "Total lipid (fat)" -> fatPer100g = nutrient.getValue();
//                }
//            }
//        }
//
//        double calories = (caloriesPer100g * quantityInGrams) / 100.0;
//        double protein  = (proteinPer100g  * quantityInGrams) / 100.0;
//        double carbs    = (carbsPer100g    * quantityInGrams) / 100.0;
//        double fat      = (fatPer100g      * quantityInGrams) / 100.0;
//
//        FoodResponseDto dto = new FoodResponseDto();
//        dto.setF(foodId);
//        dto.setFoodName(foodItem.getDescription());
//        dto.setQuantity(quantityInGrams);
//        dto.setCalories(calories);
//        dto.setProtein(protein);
//        dto.setCarbs(carbs);
//        dto.setFat(fat);
//
//        return dto;
//    }
}