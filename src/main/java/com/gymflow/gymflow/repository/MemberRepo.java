package com.gymflow.gymflow.repository;

import com.gymflow.gymflow.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member,Long> {
    @Override
    Optional<Member> findById(Long aLong);
}
