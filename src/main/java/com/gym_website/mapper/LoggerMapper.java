package com.gym_website.mapper;

import com.gym_website.dto.LoggerDto;
import com.gym_website.entity.LoggerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoggerMapper {
    LoggerEntity toEntity(LoggerDto dto);
    LoggerDto toDto(LoggerEntity entity);

    List<LoggerDto> toDtos(List<LoggerEntity> loggerEntities);
}
