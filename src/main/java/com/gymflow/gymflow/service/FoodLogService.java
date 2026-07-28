package com.gymflow.gymflow.service;

import com.gymflow.gymflow.dto.FoodLogRequestDto;
import com.gymflow.gymflow.dto.FoodLogResponseDto;

import java.util.List;

public interface FoodLogService {
    FoodLogResponseDto saveMeal(FoodLogRequestDto requestDto);
    List<FoodLogResponseDto> getLogsByMember(Long memberId);
}
