package com.gym_website.service;

import com.gym_website.dto.ExercisesDto;
import com.gym_website.dto.ResponseDto;
import com.gym_website.entity.ExercisesEntity;
import com.gym_website.mapper.ExercisesMapper;
import org.apache.coyote.Response;
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

    public ResponseDto deleteExercise(Long id){
        ResponseDto responseDto = new ResponseDto();
        if (exercisesRepository.findById(id).orElse(null)!=null){
            exercisesRepository.delete(exercisesRepository.findById(id).orElse(null));
            responseDto.setSuccessMessage("Exercise deleted");
        }
        responseDto.setErrorMessage("Exercise not found");
        return  responseDto;
    }

    public ResponseDto editExercise(ExercisesDto exercisesDto){
        ExercisesEntity exercisesEntity = exercisesRepository.findById(exercisesDto.getId()).orElse(null);
        exercisesEntity.setName(exercisesDto.getName());
        exercisesEntity.setCategory(exercisesDto.getCategory());
        exercisesEntity.setDescription(exercisesDto.getDescription());
        exercisesRepository.save(exercisesEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Exercise modified");
        return responseDto;
    }


}
