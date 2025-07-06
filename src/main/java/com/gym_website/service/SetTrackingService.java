package com.gym_website.service;

import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.SetTrackingDto;
import com.gym_website.entity.SetTrackingEntity;
import com.gym_website.mapper.SetTrackingMapper;
import com.gym_website.repository.SetTrackingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetTrackingService {

    private final SetTrackingMapper setTrackingMapper;
    private final SetTrackingRepository setTrackingRepository;

    public SetTrackingService(SetTrackingMapper setTrackingMapper, SetTrackingRepository setTrackingRepository) {
        this.setTrackingMapper = setTrackingMapper;
        this.setTrackingRepository = setTrackingRepository;
    }

    public ResponseDto addSetTracking(SetTrackingDto setTrackingDto) {
        ResponseDto responseDto = new ResponseDto();
        SetTrackingEntity setTrackingEntity = setTrackingMapper.toEntity(setTrackingDto);
        setTrackingRepository.save(setTrackingEntity);
        responseDto.setSuccessMessage("Set tracking added");
        return responseDto;
    }

    public SetTrackingDto getSetTracking(Long id) {
        SetTrackingEntity setTrackingEntity = setTrackingRepository.findById(id).orElse(null);
        return setTrackingMapper.toDto(setTrackingEntity);
    }

    public List<SetTrackingDto> getAllSetTrackings() {
        List<SetTrackingEntity> setTrackingEntities = setTrackingRepository.findAll();
        return setTrackingMapper.toDtos(setTrackingEntities);
    }

    public List<SetTrackingDto> getSetTrackingsByWorkoutId(Long workoutId) {
        List<SetTrackingEntity> setTrackingEntities = setTrackingRepository.findByWorkoutId(workoutId);
        return setTrackingMapper.toDtos(setTrackingEntities);
    }

    public List<SetTrackingDto> getSetTrackingsByExerciseId(Long exerciseId) {
        List<SetTrackingEntity> setTrackingEntities = setTrackingRepository.findByExerciseId(exerciseId);
        return setTrackingMapper.toDtos(setTrackingEntities);
    }

    public List<SetTrackingDto> getSetTrackingsByWorkoutIdAndExerciseId(Long workoutId, Long exerciseId) {
        List<SetTrackingEntity> setTrackingEntities = setTrackingRepository.findByWorkoutIdAndExerciseId(workoutId, exerciseId);
        return setTrackingMapper.toDtos(setTrackingEntities);
    }

    public ResponseDto editSetTracking(SetTrackingDto setTrackingDto) {
        ResponseDto responseDto = new ResponseDto();
        if (setTrackingRepository.findById(setTrackingDto.getId()).isPresent()) {
            SetTrackingEntity setTrackingEntity = setTrackingMapper.toEntity(setTrackingDto);
            setTrackingRepository.save(setTrackingEntity);
            responseDto.setSuccessMessage("Set tracking updated");
        } else {
            responseDto.setErrorMessage("Set tracking not found");
        }
        return responseDto;
    }

    public ResponseDto deleteSetTracking(Long id) {
        ResponseDto responseDto = new ResponseDto();
        if (setTrackingRepository.findById(id).isPresent()) {
            setTrackingRepository.deleteById(id);
            responseDto.setSuccessMessage("Set tracking deleted");
        } else {
            responseDto.setErrorMessage("Set tracking not found");
        }
        return responseDto;
    }
}
