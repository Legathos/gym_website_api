package service;

import dto.ExercisesDto;
import entity.ExercisesEntity;
import mapper.ExercisesMapper;
import org.springframework.stereotype.Service;
import repository.ExercisesRepository;

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
