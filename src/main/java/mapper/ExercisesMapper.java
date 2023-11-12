package mapper;

import dto.ExercisesDto;
import entity.ExercisesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExercisesMapper {
    ExercisesEntity toEntity (ExercisesDto dto);
    ExercisesDto toDto(ExercisesEntity entity);
}
