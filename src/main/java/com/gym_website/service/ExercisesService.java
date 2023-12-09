package com.gym_website.service;

import com.gym_website.dto.ExercisesDto;
import com.gym_website.entity.ExercisesEntity;
import com.gym_website.mapper.ExercisesMapper;
import org.springframework.stereotype.Service;
import com.gym_website.repository.ExercisesRepository;

import java.util.Optional;

@Service
public class ExercisesService {

    private final ExercisesMapper exercisesMapper;
    private final ExercisesRepository exercisesRepository;
    public ExercisesService(ExercisesMapper exercisesMapper, ExercisesRepository exercisesRepository){

        this.exercisesMapper = exercisesMapper;
        this.exercisesRepository = exercisesRepository;
    }

    public void addExercise(ExercisesDto exercisesDto){
        ExercisesEntity exercisesEntity = exercisesMapper.toEntity(exercisesDto);
        exercisesRepository.save(exercisesEntity);
    }

    public ExercisesDto getExercise (Long exerciseId){
        Optional<ExercisesEntity> exercisesEntity = exercisesRepository.findById(exerciseId);
        return  exercisesEntity.map(exercisesMapper::toDto).orElse(null);
    }



}
