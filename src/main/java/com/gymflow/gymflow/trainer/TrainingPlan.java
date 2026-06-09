package com.gymflow.gymflow.trainer;

import com.gymflow.gymflow.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goal;
    private String assignedTrainer;
    private Double durationWeeks;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
