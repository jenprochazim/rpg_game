package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.dto.locationDTO.LocationRadiusDTO;

import java.util.List;

public interface LocationService {
    void createLocation(LocationDTO newLocation);

    void updateLocation(LocationDTO updatedLocation);

    LocationDTO getActualLocation(Integer p, Integer r);

    List<LocationDTO> getAllLocations();

    List<LocationDTO> getNearLocation(Integer p, Integer r);

    List<LocationDTO> getNorthLocation(Integer p, Integer r);

    List<LocationDTO> getSouthLocation(Integer p, Integer r);

    List<LocationDTO> getEastLocation(Integer p, Integer r);

    List<LocationDTO> getWestLocation(Integer p, Integer r);

    void updateLocationRadius(LocationRadiusDTO locationRadiusDTO);
}
