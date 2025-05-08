package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.entity.LocationEntity;
import cz.jenprochazim.rpg_game.mapper.LocationMapper;
import cz.jenprochazim.rpg_game.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;


    @Override
    public void createLocation(LocationDTO newLocation) {
        if (!isLocationUnique(newLocation.getR(), newLocation.getP())) {
            throw new IllegalArgumentException("Lokace na techto souradnicich jiz existuje");
        }
        LocationEntity location = locationMapper.toLocationEntity(newLocation);
        locationRepository.save(location);
    }

    @Override
    public void updateLocation(LocationDTO updatedLocation) {
        LocationEntity location = getLocationByCoordinates(updatedLocation.getR(), updatedLocation.getP());
        if (updatedLocation.getName() != null && !updatedLocation.getName().isBlank()) {
            location.setName(updatedLocation.getName());
        }
        if (updatedLocation.getDescription() != null && !updatedLocation.getDescription().isBlank()) {
            location.setDescription(updatedLocation.getDescription());
        }
        if (updatedLocation.getTerrainType() != null) {
            location.setTerrainType(updatedLocation.getTerrainType());
        }
        if (updatedLocation.getNote() != null && !updatedLocation.getNote().isBlank()) {
            location.setNote(updatedLocation.getNote());
        }
        locationRepository.save(location);
    }

    @Override
    public LocationDTO getActualLocation(Integer p, Integer r) {
        return locationMapper.toLocationDTO(getLocationByCoordinates(p,r));
    }

    private LocationEntity getLocationByCoordinates(int r, int p) {
        return locationRepository.findByRAndP(r, p).orElseThrow(() -> new NoSuchElementException("Na souřadnicích r: " + r + "; p: " + p + " se nenachází žádná lokace."));
     }

    private boolean isLocationUnique(int r, int p) {
        return locationRepository.findByRAndP(r, p).isEmpty();
    }
}
