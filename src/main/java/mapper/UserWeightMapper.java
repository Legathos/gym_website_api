package mapper;

import dto.UserWeightDto;
import entity.UserWeightEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWeightMapper {
    UserWeightEntity toEntity(UserWeightDto dto);
    UserWeightDto toDto(UserWeightEntity entity);
}
