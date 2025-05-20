package cz.jenprochazim.rpg_game.mapper;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationAttributeDTO;
import cz.jenprochazim.rpg_game.entity.LocationAttributeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationAttributeMapper {
    LocationAttributeEntity toLocationAttributeEntity(LocationAttributeDTO locationAttributeDTO);
    LocationAttributeDTO toLocationAttributeDTO(LocationAttributeEntity locationAttributeEntity);
}
