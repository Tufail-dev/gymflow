package com.gymflow.gymflow.repository;

import com.gymflow.gymflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByusername( String userRequestDto);


    Optional<User> findByemail(String email);
}
