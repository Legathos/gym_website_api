package com.gym_website.service;

import com.gym_website.dto.ExercisesDto;
import com.gym_website.dto.ResponseDto;
import com.gym_website.entity.ExercisesEntity;
import com.gym_website.mapper.ExercisesMapper;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import com.gym_website.repository.ExercisesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExercisesService {

    private final ExercisesMapper exercisesMapper;
    private final ExercisesRepository exercisesRepository;
    public ExercisesService(ExercisesMapper exercisesMapper, ExercisesRepository exercisesRepository){

        this.exercisesMapper = exercisesMapper;
        this.exercisesRepository = exercisesRepository;
    }

    public ResponseDto addExercise(ExercisesDto exercisesDto){
        ExercisesEntity exercisesEntity = exercisesMapper.toEntity(exercisesDto);
        exercisesRepository.save(exercisesEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Exercise added");
        return responseDto;
    }

    public ExercisesDto getExercise (Long id){
        ExercisesEntity exercisesEntity = exercisesRepository.findById(id).orElse(null);
        return  exercisesMapper.toDto(exercisesEntity);
    }

    public List<ExercisesDto> gelAllExercises (){
        List<ExercisesEntity> exercisesEntities = exercisesRepository.findAll();
        return exercisesMapper.toDtos(exercisesEntities);
    }

    public List<ExercisesDto> gelExercisesByCategory (String category){
        List<ExercisesEntity> exercisesEntities = exercisesRepository.findByCategory(category);
        return exercisesMapper.toDtos(exercisesEntities);
    }


    public ResponseDto deleteExercise(Long id){
        ResponseDto responseDto = new ResponseDto();
        if (exercisesRepository.findById(id).orElse(null)!=null){
            exercisesRepository.delete(exercisesRepository.findById(id).orElse(null));
            responseDto.setSuccessMessage("Exercise deleted");
        }else{
        responseDto.setErrorMessage("Exercise not found");
        }
        return responseDto;
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
