package mapper;

import dto.FoodDto;
import entity.FoodEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    FoodDto toDto (FoodEntity entity);
    FoodEntity toEntity(FoodDto dto);
}
