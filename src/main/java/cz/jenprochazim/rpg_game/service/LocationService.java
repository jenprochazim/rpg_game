package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;

public interface LocationService {
    void createLocation(LocationDTO newLocation);

    void updateLocation(LocationDTO updatedLocation);

    LocationDTO getActualLocation(Integer p, Integer r);
}
