package mapper;

import dto.SetTrackingDto;
import entity.SetTrackingEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SetTrackingMapper {
    SetTrackingEntity toEntity(SetTrackingDto dto);
    SetTrackingDto toDto(SetTrackingEntity entity);
}
