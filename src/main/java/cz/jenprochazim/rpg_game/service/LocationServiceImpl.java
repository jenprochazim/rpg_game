package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.entity.LocationEntity;
import cz.jenprochazim.rpg_game.mapper.LocationMapper;
import cz.jenprochazim.rpg_game.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        if (!isLocationUnique(newLocation.getP(), newLocation.getR())) {
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
        return locationMapper.toLocationDTO(getLocationByCoordinates(r, p));
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(location -> locationMapper.toLocationDTO(location))
                .toList();
    }

    @Override
    public List<LocationDTO> getNearLocation(Integer p, Integer r) {
        int[][] offsets = {
                {0, -1},
                {1, -1},
                {-1, 0},
                {1, 0},
                {-1, 1},
                {0, 1}
        };
        return getGroupOfLocations(p, r, offsets);
    }

    private List<LocationDTO> getGroupOfLocations(Integer p, Integer r, int[][] offsets) {
        List<LocationDTO> locations = new ArrayList<>();
        for (int[] offset : offsets) {
            try {
                locations.add(locationMapper.toLocationDTO(getLocationByCoordinates(p + offset[0], r + offset[1])));
            } catch (NoSuchElementException ignored) {

            }
        }
        return locations;
    }

    private LocationEntity getLocationByCoordinates(int p, int r) {
        return locationRepository.findByPAndR(p, r).orElseThrow(() -> new NoSuchElementException("Na souřadnicích p: " + p + "; r: " + r + " se nenachází žádná lokace."));
    }

    private boolean isLocationUnique(int p, int r) {
        return locationRepository.findByPAndR(p, r).isEmpty();
    }
}
