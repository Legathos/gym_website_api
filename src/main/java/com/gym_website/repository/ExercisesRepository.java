package com.gym_website.repository;

import com.gym_website.entity.ExercisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercisesRepository extends JpaRepository<ExercisesEntity, Long> {
}
