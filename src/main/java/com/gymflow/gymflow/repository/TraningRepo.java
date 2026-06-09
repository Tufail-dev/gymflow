package com.gymflow.gymflow.repository;

import com.gymflow.gymflow.trainer.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraningRepo extends JpaRepository<TrainingPlan,Long> {

}
