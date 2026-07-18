    package com.gymflow.gymflow.controller;

    import com.gymflow.gymflow.dto.FoodResponceDto;
    import com.gymflow.gymflow.external.dto.UsdaSearchResponse;
    import com.gymflow.gymflow.service.FoodService;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/foods")

    public class FoodController {

        private FoodService foodService;

        public FoodController(FoodService foodService) {
            this.foodService = foodService;
        }
        @GetMapping("/search")
       public FoodResponceDto searchFood(@RequestParam  String foodName,double quantityInGrams){
            return  foodService.searchFood(foodName,quantityInGrams);


        }
    }
