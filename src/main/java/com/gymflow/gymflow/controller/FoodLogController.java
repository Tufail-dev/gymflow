package com.gymflow.gymflow.controller;

import com.gymflow.gymflow.dto.FoodLogRequestDto;
import com.gymflow.gymflow.dto.FoodLogResponseDto;
import com.gymflow.gymflow.service.FoodLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-logs")
public class FoodLogController {

    private final FoodLogService foodLogService;

    public FoodLogController(FoodLogService foodLogService) {
        this.foodLogService = foodLogService;
    }
@PostMapping
    public ResponseEntity<FoodLogResponseDto> saveMeal(@RequestBody FoodLogRequestDto requestDto) {
        FoodLogResponseDto response = foodLogService.saveMeal(requestDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<FoodLogResponseDto>> getLogsByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(foodLogService.getLogsByMember(memberId));
    }
}