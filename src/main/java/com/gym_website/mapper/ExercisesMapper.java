package com.gym_website.mapper;

import com.gym_website.dto.ExercisesDto;
import com.gym_website.entity.ExercisesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExercisesMapper {
    ExercisesEntity toEntity (ExercisesDto dto);
    ExercisesDto toDto(ExercisesEntity entity);
}
