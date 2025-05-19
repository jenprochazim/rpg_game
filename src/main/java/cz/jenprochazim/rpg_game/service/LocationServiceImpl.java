package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.entity.LocationEntity;
import cz.jenprochazim.rpg_game.entity.enums.TerrainType;
import cz.jenprochazim.rpg_game.mapper.LocationMapper;
import cz.jenprochazim.rpg_game.repository.LocationRepository;
import cz.jenprochazim.rpg_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private UserService userService;

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
        LocationEntity location = getLocationByCoordinates(updatedLocation.getP(), updatedLocation.getR());
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
        getConsoleMap(p, r, 2);
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

    @Override
    public List<LocationDTO> getNorthLocation(Integer p, Integer r) {
        int[][] offsets = {
                {0, -2},
                {1, -2},
                {2, -2},
        };
        return getGroupOfLocations(p, r, offsets);
    }

    @Override
    public List<LocationDTO> getSouthLocation(Integer p, Integer r) {
        int[][] offsets = {
                {-2, 2},
                {-1, 2},
                {0, 2},
        };
        return getGroupOfLocations(p, r, offsets);
    }

    @Override
    public List<LocationDTO> getEastLocation(Integer p, Integer r) {
        int[][] offsets = {
                {2, -1},
                {2, 0},
                {1, 1},
        };
        return getGroupOfLocations(p, r, offsets);
    }

    @Override
    public List<LocationDTO> getWestLocation(Integer p, Integer r) {
        int[][] offsets = {
                {-1, -1},
                {-2, 0},
                {-2, 1},
        };
        return getGroupOfLocations(p, r, offsets);
    }

    private List<LocationDTO> getGroupOfLocations(Integer p, Integer r, int[][] offsets) {
        List<LocationDTO> locations = new ArrayList<>();
        for (int[] offset : offsets) {
            LocationEntity location;
            try {
                location = getLocationByCoordinates(p + offset[0], r + offset[1]);
                locations.add(locationMapper.toLocationDTO(location));
            } catch (NoSuchElementException ignored) {
            }

        }
        return locations;
    }

    private LocationEntity getLocationByCoordinates(int p, int r) {
        LocationEntity entity;
        try {
            entity = locationRepository.findByPAndR(p, r).orElseThrow(() -> new NoSuchElementException("Na souřadnicích p: " + p + "; r: " + r + " se nenachází žádná lokace."));
        } catch (NoSuchElementException ignored) {
            createEmptyLocation(p, r);
            entity = locationRepository.findByPAndR(p, r).orElseThrow(() -> new NoSuchElementException("Na souřadnicích p: " + p + "; r: " + r + " se nenachází žádná lokace."));
        }
        return entity;
    }

    private void createEmptyLocation(int p, int r) {
        LocationDTO newLocation = new LocationDTO();
        newLocation.setP(p);
        newLocation.setR(r);
        newLocation.setTerrainType(TerrainType.EMPTY);
        newLocation.setCreator(userService.getUser(1L));
        createLocation(newLocation);
    }

    private boolean isLocationUnique(int p, int r) {
        return locationRepository.findByPAndR(p, r).isEmpty();
    }

    private void getConsoleMap(int centerP, int centerR, int radius) {
        StringBuilder consoleMap = new StringBuilder();
        for (int r = -radius; r <= radius; r++) {
            consoleMap.append("  ".repeat(Math.max(0, r)));
            for (int p = -radius; p <= radius; p++) {
                if (Math.abs(p + r) > radius) {
                    consoleMap.append("  ");
                } else {
                    String terrainSymbol = "";

                    TerrainType terrainType = getLocationByCoordinates(centerP + p, centerR + r).getTerrainType();
                    switch (terrainType) {
                        case PATH:
                            terrainSymbol += "-><-";
                            break;
                        case PLAINS:
                            terrainSymbol += "////";
                            break;
                        case FORREST:
                            terrainSymbol += "VVVV";
                            break;
                        default:
                            terrainSymbol += "XXXX";
                            break;
                    }
                    consoleMap.append(terrainSymbol);
                }
            }
            consoleMap.append("\n");
        }
        System.out.println(consoleMap);
    }
}
