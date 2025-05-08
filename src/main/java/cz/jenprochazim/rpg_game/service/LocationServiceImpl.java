package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.entity.LocationEntity;
import cz.jenprochazim.rpg_game.mapper.LocationMapper;
import cz.jenprochazim.rpg_game.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;


    @Override
    public void createLocation(LocationDTO newLocation) {
        System.out.println("service DTO>> " + newLocation.getTerrainType());
        LocationEntity location = locationMapper.toLocationEntity(newLocation);
        System.out.println("service entity>> " + location.getTerrainType());
        locationRepository.save(location);
    }
}
