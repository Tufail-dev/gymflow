package com.gymflow.gymflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String foodName;
    @Positive
    private double quantity;
    private String unit ;
    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    private LocalDateTime searchedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="User_id")
    private User user;


}
