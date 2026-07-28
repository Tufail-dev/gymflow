package com.gymflow.gymflow.service;

import com.gymflow.gymflow.dto.FoodLogRequestDto;
import com.gymflow.gymflow.dto.FoodLogResponseDto;
import com.gymflow.gymflow.entity.FoodLog;
import com.gymflow.gymflow.entity.Member;
import com.gymflow.gymflow.external.UsdaApiService;
import com.gymflow.gymflow.external.dto.ScaledFoodResponse;
import com.gymflow.gymflow.repository.FoodLogRepo;

import com.gymflow.gymflow.repository.MemberRepo;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodLogServiceImpl implements FoodLogService {

    private final UsdaApiService usdaApiService;
    private final FoodLogRepo foodLogRepo;
    private final MemberRepo memberRepo;
    private final ModelMapper modelMapper;



    public FoodLogServiceImpl(
            UsdaApiService usdaApiService,
            FoodLogRepo foodLogRepo,
            MemberRepo memberRepo,
            ModelMapper modelMapper) {

        this.usdaApiService = usdaApiService;
        this.foodLogRepo = foodLogRepo;
        this.memberRepo = memberRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public FoodLogResponseDto saveMeal(FoodLogRequestDto requestDto) {

        ScaledFoodResponse scaledData =
                usdaApiService.getScaledFoodDetails(
                        requestDto.getFoodId(),
                        requestDto.getQuantity());

        Member member = memberRepo.findById(requestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        FoodLog foodLog = new FoodLog();

        foodLog.setCalories(scaledData.getCalories());
        foodLog.setProtein(scaledData.getProtein());
        foodLog.setFat(scaledData.getFat());
        foodLog.setCarbs(scaledData.getCarbs());
        foodLog.setQuantity(requestDto.getQuantity());
        foodLog.setFoodName(scaledData.getFoodName());   // ya getDescription()
        foodLog.setMealType(requestDto.getMealType());
        foodLog.setConsumedAt(LocalDateTime.now());
        foodLog.setMember(member);

        FoodLog savedLog = foodLogRepo.save(foodLog);

        return modelMapper.map(savedLog, FoodLogResponseDto.class);
    }

    @Override
    public List<FoodLogResponseDto> getLogsByMember(Long memberId) {

        List<FoodLog> foodLogs= foodLogRepo.findByMemberId(memberId);

        return foodLogs.stream()
                .map(log->modelMapper.map(log,FoodLogResponseDto.class))
                .collect(Collectors.toList());
    }
}