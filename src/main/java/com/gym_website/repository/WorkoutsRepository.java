package com.gym_website.repository;

import com.gym_website.entity.UserWeightEntity;
import com.gym_website.entity.WorkoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutsRepository extends JpaRepository<WorkoutsEntity, Long> {

    @Query("SELECT w FROM WorkoutsEntity w WHERE w.user_id = :userId")
    List<WorkoutsEntity> findByUserId(@Param("userId") Long userId);
}
