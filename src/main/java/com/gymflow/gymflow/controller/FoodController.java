package com.gymflow.gymflow.controller;

import com.gymflow.gymflow.dto.FoodItemDto;
import com.gymflow.gymflow.service.FoodSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodSearchService foodSearchService;

    public FoodController(FoodSearchService foodSearchService) {
        this.foodSearchService = foodSearchService;
    }

    @GetMapping("/search")
    public List<FoodItemDto> searchFood(@RequestParam String query) {
        return foodSearchService.searchFoodWithNutrition(query);
    }
}