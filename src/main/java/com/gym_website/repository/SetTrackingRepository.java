package com.gym_website.repository;

import com.gym_website.entity.SetTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetTrackingRepository extends JpaRepository<SetTrackingEntity, Long> {

    @Query("SELECT s FROM SetTrackingEntity s WHERE s.workout_id = :workoutId")
    List<SetTrackingEntity> findByWorkoutId(@Param("workoutId") Long workoutId);

    @Query("SELECT s FROM SetTrackingEntity s WHERE s.exercise_id = :exerciseId")
    List<SetTrackingEntity> findByExerciseId(@Param("exerciseId") Long exerciseId);

    @Query("SELECT s FROM SetTrackingEntity s WHERE s.workout_id = :workoutId AND s.exercise_id = :exerciseId")
    List<SetTrackingEntity> findByWorkoutIdAndExerciseId(@Param("workoutId") Long workoutId, @Param("exerciseId") Long exerciseId);
}
