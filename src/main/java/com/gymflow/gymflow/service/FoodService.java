package com.gymflow.gymflow.service;

import com.gymflow.gymflow.dto.FoodResponceDto;
import com.gymflow.gymflow.exception.ResourceNotFoundException;
import com.gymflow.gymflow.external.UsdaApiService;
import com.gymflow.gymflow.external.dto.UsdaFoodItem;
import com.gymflow.gymflow.external.dto.UsdaNutrient;
import com.gymflow.gymflow.external.dto.UsdaSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class FoodService {




        @Autowired
        private UsdaApiService usdaApiService;
     public    FoodResponceDto searchFood(String foodName,double quantityInGrams){
        UsdaSearchResponse usdaSearchResponse1 = usdaApiService.searchFood(foodName);
        FoodResponceDto foodResponceDto = new FoodResponceDto();
        List<UsdaFoodItem>  usdaFoodItem= usdaSearchResponse1.getFoods();
        if(usdaFoodItem.isEmpty()){
            throw new ResourceNotFoundException("No food found for: " + foodName);
        }


         foodResponceDto.setFoodName(usdaFoodItem.get(0).getDescription());

            List< UsdaNutrient> nutrients = usdaFoodItem.get(0).getFoodNutrients();
         double factor = quantityInGrams / 100.0;

         foodResponceDto.setQuantityInGrams(quantityInGrams);
            for (UsdaNutrient nutrient:nutrients){
                if (nutrient.getNutrientName().equals("Energy")) {
                        foodResponceDto.setCalorie(nutrient.getValue()* factor);

                }
                if(nutrient.getNutrientName().equals("Protein")){
                    foodResponceDto.setProtien(nutrient.getValue()* factor);
                }
                if ("Carbohydrate, by difference".equals(nutrient.getNutrientName())) {
                   foodResponceDto.setCarbs(nutrient.getValue()* factor);
                }

                if ("Total lipid (fat)".equals(nutrient.getNutrientName())) {
                   foodResponceDto.setFat(nutrient.getValue()* factor);
                }

            }

            return foodResponceDto;
        }
    }
