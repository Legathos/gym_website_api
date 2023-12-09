package com.gym_website.mapper;

import com.gym_website.dto.LoggerDto;
import com.gym_website.entity.LoggerEntity;
import org.mapstruct.Mapper;

@Mapper
public interface LoggerMapper {
    LoggerEntity toEntity(LoggerDto dto);
    LoggerDto toDto(LoggerEntity entity);
}
