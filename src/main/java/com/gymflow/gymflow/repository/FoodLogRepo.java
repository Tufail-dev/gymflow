    package com.gymflow.gymflow.repository;


    import com.gymflow.gymflow.entity.FoodLog;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface FoodLogRepo extends JpaRepository<FoodLog, Long> {
        List<FoodLog> findByMemberId(Long memberId);
    }
