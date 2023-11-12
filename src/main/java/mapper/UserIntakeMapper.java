package mapper;

import dto.UserIntakeDto;
import entity.UserIntakeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserIntakeMapper {
    UserIntakeEntity toEntity(UserIntakeDto dto);
    UserIntakeDto toDto(UserIntakeEntity entity);
}
