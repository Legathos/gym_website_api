package com.gym_website.mapper;

import com.gym_website.dto.UserWeightDto;
import com.gym_website.entity.UserWeightEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWeightMapper {
    UserWeightEntity toEntity(UserWeightDto dto);
    UserWeightDto toDto(UserWeightEntity entity);
}
