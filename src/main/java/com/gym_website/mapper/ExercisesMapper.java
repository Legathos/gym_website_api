package com.gym_website.mapper;

import com.gym_website.dto.ExercisesDto;
import com.gym_website.entity.ExercisesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExercisesMapper {
    ExercisesEntity toEntity (ExercisesDto dto);
    ExercisesDto toDto(ExercisesEntity entity);
    List<ExercisesDto> toDtos(List<ExercisesEntity> entities);
}
