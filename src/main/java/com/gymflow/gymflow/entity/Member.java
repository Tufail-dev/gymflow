package com.gymflow.gymflow.entity;

import com.gymflow.gymflow.trainer.TrainingPlan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="gymMembers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private String email;
    private String phone;
    private String membershipType;
    private Double dailyCalorieGoal;
    private Double dailyProteinGoal;
    private String goal;


    @OneToMany(mappedBy = "member" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<TrainingPlan> trainingPlanList;


    @OneToOne
    @JoinColumn(name="user_id")
    private  User user;

    @OneToMany(mappedBy ="member1" )
    private List<Food> foodList;

    @OneToMany(mappedBy ="member")
    private List<FoodLog> foodLogs;

}
