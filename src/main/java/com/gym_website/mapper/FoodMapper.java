package com.gym_website.mapper;

import com.gym_website.dto.FoodDto;
import com.gym_website.entity.FoodEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    FoodDto toDto (FoodEntity entity);
    FoodEntity toEntity(FoodDto dto);
}
