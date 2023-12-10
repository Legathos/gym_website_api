package com.gym_website.mapper;

import com.gym_website.dto.WorkoutsDto;
import com.gym_website.entity.WorkoutsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutsMapper {

    WorkoutsDto toDto(WorkoutsEntity workoutsEntity);

    WorkoutsEntity toEntity(WorkoutsDto workoutsDto);
    List<WorkoutsDto> toDtos(List<WorkoutsEntity> workoutsEntities);
}
