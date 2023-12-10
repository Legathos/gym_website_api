package com.gym_website.repository;

import com.gym_website.entity.ExercisesEntity;
import com.gym_website.entity.UserWeightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercisesRepository extends JpaRepository<ExercisesEntity, Long> {

    @Query("SELECT ee FROM ExercisesEntity ee WHERE ee.category = :category")
    List<ExercisesEntity> findByCategory(@Param("category") String category);
}
