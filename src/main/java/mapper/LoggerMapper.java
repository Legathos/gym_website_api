package mapper;

import dto.LoggerDto;
import entity.LoggerEntity;
import org.mapstruct.Mapper;

@Mapper
public interface LoggerMapper {
    LoggerEntity toEntity(LoggerDto dto);
    LoggerDto toDto(LoggerEntity entity);
}
