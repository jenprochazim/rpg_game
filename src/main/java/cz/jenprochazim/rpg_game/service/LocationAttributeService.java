package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationAttributeDTO;
import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;

import java.util.List;

public interface LocationAttributeService {
    void createAttribute(LocationAttributeDTO locationAttributeDTO, Integer p, Integer r);

    List<LocationAttributeDTO> getAttributesByLocation(Integer p, Integer r);

    void deleteAttribute(Integer attributeID);

    List<LocationAttributeDTO> getAllAttributes();
}
