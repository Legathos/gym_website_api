package com.gym_website.service;

import com.gym_website.dto.ExercisesDto;
import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.UserDto;
import com.gym_website.entity.ExercisesEntity;
import com.gym_website.mapper.ExercisesMapper;
import org.springframework.stereotype.Service;
import com.gym_website.repository.ExercisesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExercisesService {

    private final ExercisesMapper exercisesMapper;
    private final ExercisesRepository exercisesRepository;
    private final UserService userService;

    public ExercisesService(ExercisesMapper exercisesMapper, ExercisesRepository exercisesRepository, UserService userService){
        this.exercisesMapper = exercisesMapper;
        this.exercisesRepository = exercisesRepository;
        this.userService = userService;
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

    public List<ExercisesDto> getExercisesByUserId (Long userId){
        // Get user-specific exercises
        List<ExercisesEntity> userExercises = exercisesRepository.findByUserId(userId);

        // Get default exercises (associated with system user)
        // Find system user by username
        UserDto systemUser = userService.getUserInfoByUsername("system");
        if (systemUser != null) {
            List<ExercisesEntity> defaultExercises = exercisesRepository.findByUserId(systemUser.getId());

            // Combine both lists
            List<ExercisesEntity> allExercises = new ArrayList<>(userExercises);
            allExercises.addAll(defaultExercises);

            return exercisesMapper.toDtos(allExercises);
        }

        // If system user doesn't exist, just return user exercises
        return exercisesMapper.toDtos(userExercises);
    }


    public ResponseDto deleteExercise(Long id){
        ResponseDto responseDto = new ResponseDto();
        if (exercisesRepository.findById(id).orElse(null)!=null){
            exercisesRepository.delete(Objects.requireNonNull(exercisesRepository.findById(id).orElse(null)));
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
        exercisesEntity.setUser_id(exercisesDto.getUser_id());
        exercisesRepository.save(exercisesEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Exercise modified");
        return responseDto;
    }


}
