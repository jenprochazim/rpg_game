package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationAttributeDTO;
import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;

public interface LocationAttributeService {
    void createAttribute(LocationAttributeDTO locationAttributeDTO, Integer p, Integer r);
}
