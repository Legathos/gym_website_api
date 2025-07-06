package com.gym_website.mapper;

import com.gym_website.dto.UserWeightDto;
import com.gym_website.entity.UserWeightEntity;
import com.gym_website.repository.UserWeightRepository;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserWeightMapper {
    UserWeightEntity toEntity(UserWeightDto dto);
    UserWeightDto toDto(UserWeightEntity entity);
    List<UserWeightEntity> toEntities(List<UserWeightDto> dtos);
    List<UserWeightDto> toDtos(List<UserWeightEntity> entities);
}
