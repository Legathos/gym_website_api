package com.gym_website.mapper;

import com.gym_website.dto.UserDto;
import com.gym_website.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserEntity entity);
    UserEntity toEntity(UserDto dto);

    List<UserDto> toDtos(List<UserEntity> userEntities);
}

