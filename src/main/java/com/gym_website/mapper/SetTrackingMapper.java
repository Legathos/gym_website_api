package com.gym_website.mapper;

import com.gym_website.dto.SetTrackingDto;
import com.gym_website.entity.SetTrackingEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SetTrackingMapper {
    SetTrackingEntity toEntity(SetTrackingDto dto);
    SetTrackingDto toDto(SetTrackingEntity entity);
}
