package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;

import java.util.List;

public interface LocationService {
    void createLocation(LocationDTO newLocation);

    void updateLocation(LocationDTO updatedLocation);

    LocationDTO getActualLocation(Integer p, Integer r);

    List<LocationDTO> getAllLocations();
}
