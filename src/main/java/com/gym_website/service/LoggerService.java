package com.gym_website.service;

import com.gym_website.dto.LoggerDto;
import com.gym_website.dto.ResponseDto;
import com.gym_website.entity.LoggerEntity;
import com.gym_website.mapper.LoggerMapper;
import com.gym_website.repository.LoggerRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoggerService {

    private final LoggerRepository loggerRepository;
    private  final LoggerMapper loggerMapper;

    public LoggerService(LoggerRepository loggerRepository, LoggerMapper loggerMapper) {
        this.loggerRepository = loggerRepository;
        this.loggerMapper = loggerMapper;
    }

    public ResponseDto addNewLog(LoggerDto loggerDto){
        LoggerEntity loggerEntity  = loggerMapper.toEntity(loggerDto);
        loggerRepository.save(loggerEntity);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Log added successfully");
        return responseDto;
    }

    public List<LoggerDto> getLogsForUserIdAndDate(Long userId, Date date){
        return loggerMapper.toDtos(loggerRepository.findByUserIdAndDate(userId,date));
    }

    public ResponseDto editLog(LoggerDto loggerDto) {
    LoggerEntity loggerEntity = loggerRepository.findById(loggerDto.getId()).orElse(null);
    assert loggerEntity != null;
    loggerEntity.setCalories(loggerDto.getCalories());
    loggerEntity.setDate(loggerDto.getDate());
    loggerEntity.setFats(loggerDto.getFats());
    loggerEntity.setCarbs(loggerDto.getCarbs());
    loggerEntity.setProtein(loggerDto.getProtein());
    loggerEntity.setWeight(loggerDto.getWeight());

    loggerRepository.save(loggerEntity);

    ResponseDto responseDto = new ResponseDto();
    responseDto.setSuccessMessage("Log edited successfully");
    return responseDto;
    }

    public ResponseDto deleteLog(LoggerDto loggerDto){
        loggerRepository.delete(Objects.requireNonNull(loggerRepository.findById(loggerDto.getId()).orElse(null)));
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccessMessage("Log deleted");
        return responseDto;
    }
}
