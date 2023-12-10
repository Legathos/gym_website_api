package com.gym_website.service;

import com.gym_website.dto.ResponseDto;
import com.gym_website.dto.WorkoutsDto;
import com.gym_website.entity.WorkoutsEntity;
import com.gym_website.mapper.WorkoutsMapper;
import com.gym_website.repository.WorkoutsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutsService {

    private final WorkoutsMapper workoutsMapper;
    private final WorkoutsRepository workoutsRepository;

    public WorkoutsService(WorkoutsMapper workoutsMapper, WorkoutsRepository workoutsRepository) {
        this.workoutsMapper = workoutsMapper;
        this.workoutsRepository = workoutsRepository;
    }

    public ResponseDto addWorkout(WorkoutsDto workoutsDto){
        ResponseDto responseDto = new ResponseDto();
        WorkoutsEntity workoutsEntity = workoutsMapper.toEntity(workoutsDto);
        workoutsRepository.save(workoutsEntity);
        responseDto.setSuccessMessage("Workout added");
        return responseDto;
    }

    public WorkoutsDto getWorkout(Long id){
        WorkoutsEntity workoutsEntity = workoutsRepository.findById(id).orElse(null);
        return workoutsMapper.toDto(workoutsEntity);
    }

    public List<WorkoutsDto> getAllWorkouts(){
        List<WorkoutsEntity> workoutsEntities = workoutsRepository.findAll();
        return workoutsMapper.toDtos(workoutsEntities);
    }

    public ResponseDto editWorkout (WorkoutsDto workoutsDto){
        ResponseDto responseDto = new ResponseDto();
        WorkoutsEntity workoutsEntity = new WorkoutsEntity();
        workoutsEntity.setId(workoutsDto.getId());
        workoutsEntity.setDate(workoutsDto.getDate());
        workoutsEntity.setUser_id(workoutsDto.getUser_id());
        workoutsEntity.setName(workoutsDto.getName());
        workoutsEntity.setTotal_time(workoutsDto.getTotal_time());
        workoutsEntity.setTotal_weight(workoutsDto.getTotal_weight());
        workoutsRepository.save(workoutsEntity);
        responseDto.setSuccessMessage("Workout edited");
        return responseDto;
    }

    public ResponseDto deleteWorkout (Long id){
        ResponseDto responseDto =new ResponseDto();
        if(workoutsRepository.findById(id).isPresent()) {
            workoutsRepository.deleteById(id);
            responseDto.setSuccessMessage("Workout deleted");
            return responseDto;
        }
        responseDto.setErrorMessage("Workout not found");
        return responseDto;
    }











}

