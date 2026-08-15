package com.gymflow.gymflow.controller;

import com.gymflow.gymflow.dto.TraningRequestDto;
import com.gymflow.gymflow.dto.TraningResponceDto;
import com.gymflow.gymflow.service.TraningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-plan")
public class TraningController {
    @Autowired
    private TraningService traningService;
    @PostMapping("/add")
    public  TraningResponceDto addTrainingPlan( @Valid @RequestBody TraningRequestDto traningRequestDto){
        TraningResponceDto traningResponceDto=traningService.addTrainingPlan(traningRequestDto);
        return  traningResponceDto;
    }
    @GetMapping("/showall")

    public List<TraningResponceDto>  getalltrainingplan(){

        return traningService.getallTraningplans();
    }

    @GetMapping("{id}")

    public TraningResponceDto getalltrainingplanById(@PathVariable Long id){
        TraningResponceDto traningResponceDtos= (TraningResponceDto) traningService.getallTraningplansById(id);
        return  traningResponceDtos;
    }
    @PutMapping("{id}")
    public TraningResponceDto updateTrainingplan (@PathVariable Long id, @RequestBody TraningRequestDto traningRequestDto){
        return traningService.updateTraningplan(id,traningRequestDto);
    }

    @DeleteMapping("{id}")
    public TraningResponceDto deleteplan(@PathVariable Long id ){
        return  traningService.deleteTraningplan(id);
    }




}
