package com.gymflow.gymflow.service;

import com.gymflow.gymflow.dto.TraningRequestDto;
import com.gymflow.gymflow.dto.TraningResponceDto;
import com.gymflow.gymflow.repository.TraningRepo;
import com.gymflow.gymflow.trainer.TrainingPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraningService {
    @Autowired
    private TraningRepo traningRepo;
    @Autowired
    private ModelMapper modelMapper;
    public TraningResponceDto addTrainingPlan(TraningRequestDto traningRequestDto){
        TrainingPlan trainingPlan= modelMapper.map(traningRequestDto,TrainingPlan.class);

        Double weeklyPrice = 0.0;

        if(traningRequestDto.getGoal().equals("Muscle Gain")){
            weeklyPrice= 1200.0;
        }
        else if(traningRequestDto.getGoal().equals("Fat Loss")){
            weeklyPrice = 900.0;
        }
        else if(traningRequestDto.getGoal().equals("Strength")){
            weeklyPrice = 1500.0;
        }
        Double FinalPrice= weeklyPrice * traningRequestDto.getDurationWeeks();
        trainingPlan.setPrice(FinalPrice);
        trainingPlan.setDurationWeeks(
                traningRequestDto.getDurationWeeks()
        );
        String assignedTrainer = "";

        if(traningRequestDto.getGoal().equals("Muscle Gain")){
            assignedTrainer = "Shray";
        }
        else if(traningRequestDto.getGoal().equals("Fat Loss")){
            assignedTrainer = "Zahid";
        }
        else if(traningRequestDto.getGoal().equals("Strength")){
            assignedTrainer = "Guneet";
        }
        trainingPlan.setAssignedTrainer(assignedTrainer);
        TrainingPlan savedTrainingPlan= traningRepo.save(trainingPlan);
        TraningResponceDto traningResponceDto= modelMapper.map(savedTrainingPlan,TraningResponceDto.class);
        return  traningResponceDto;

    }
    public List<TraningResponceDto> getallTraningplans(){
       List  <TrainingPlan> trainingPlan=  traningRepo.findAll();
         List<TraningResponceDto> traningResponceDtos= trainingPlan.stream().map(t-> modelMapper.map(t,TraningResponceDto.class)).toList();

         return  traningResponceDtos;
    }
    public TraningResponceDto getallTraningplansById(Long id){
        TrainingPlan trainingPlan= traningRepo.findById(id).
                orElseThrow(()->new RuntimeException("this id is not present in db"));
        TraningResponceDto traningResponceDto= modelMapper.map(trainingPlan,TraningResponceDto.class);
        return traningResponceDto;
    }
        public TraningResponceDto updateTraningplan(Long id,TraningRequestDto traningRequestDto){
            TrainingPlan trainingPlan = traningRepo.findById(id).orElseThrow(()-> new RuntimeException(" id is not available "));

            trainingPlan.setGoal(traningRequestDto.getGoal());
            trainingPlan.setDurationWeeks(
                    traningRequestDto.getDurationWeeks()
            );
            Double weeklyPrice = 0.0;

            if(traningRequestDto.getGoal().equals("Muscle Gain")){
                weeklyPrice= 1200.0;
            }
            else if(traningRequestDto.getGoal().equals("Fat Loss")){
                weeklyPrice = 900.0;
            }
            else if(traningRequestDto.getGoal().equals("Strength")){
                weeklyPrice = 1500.0;
            }
            Double FinalPrice= weeklyPrice * traningRequestDto.getDurationWeeks();
            trainingPlan.setPrice(FinalPrice);
            trainingPlan.setDurationWeeks(
                    traningRequestDto.getDurationWeeks()
            );
            String assignedTrainer = "";

            if(traningRequestDto.getGoal().equals("Muscle Gain")){
                assignedTrainer = "Shray";
            }
            else if(traningRequestDto.getGoal().equals("Fat Loss")){
                assignedTrainer = "Zahid";
            }
            else if(traningRequestDto.getGoal().equals("Strength")){
                assignedTrainer = "Guneet";
            }
            trainingPlan.setAssignedTrainer(assignedTrainer);
            TrainingPlan savedTrainingPlan= traningRepo.save(trainingPlan);
            TraningResponceDto traningResponceDto= modelMapper.map(savedTrainingPlan,TraningResponceDto.class);
            return  traningResponceDto;

        }
        public TraningResponceDto deleteTraningplan(Long id){
        TrainingPlan trainingPlan= traningRepo.findById(id).orElseThrow(()-> new RuntimeException(" id is not exist in the db"));
TraningResponceDto traningResponceDto = modelMapper.map(trainingPlan,TraningResponceDto.class);
        traningRepo.delete(trainingPlan);
        return  traningResponceDto;

        }







        }

