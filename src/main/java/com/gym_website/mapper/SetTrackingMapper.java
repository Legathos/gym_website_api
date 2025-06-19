package com.gym_website.mapper;

import com.gym_website.dto.SetTrackingDto;
import com.gym_website.entity.SetTrackingEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SetTrackingMapper {
    SetTrackingEntity toEntity(SetTrackingDto dto);
    SetTrackingDto toDto(SetTrackingEntity entity);
    List<SetTrackingDto> toDtos(List<SetTrackingEntity> entities);
}
