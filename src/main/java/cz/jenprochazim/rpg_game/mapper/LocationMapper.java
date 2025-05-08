package cz.jenprochazim.rpg_game.mapper;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.entity.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationEntity toLocationEntity(LocationDTO locationDTO);
    LocationDTO toLocationDTO(LocationEntity locationEntity);
}
