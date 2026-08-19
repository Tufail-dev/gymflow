package com.gymflow.gymflow.controller;

import com.gymflow.gymflow.dto.FoodItemDto;
import com.gymflow.gymflow.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/search")
    public List<FoodItemDto> searchFood(@RequestParam String query) {
        return foodService.searchFood(query);
    }
}
//    @GetMapping("/{foodId}")
//    public FoodResponseDto getFoodDetails(
//            @PathVariable Long foodId,
//            @RequestParam double quantity) {
//
//        return foodService.getFoodDetails(foodId, quantity);
//    }
//}