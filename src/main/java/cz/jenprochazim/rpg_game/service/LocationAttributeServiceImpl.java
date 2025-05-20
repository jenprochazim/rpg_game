package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationAttributeDTO;
import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.entity.LocationAttributeEntity;
import cz.jenprochazim.rpg_game.mapper.LocationAttributeMapper;
import cz.jenprochazim.rpg_game.mapper.LocationMapper;
import cz.jenprochazim.rpg_game.repository.LocationAttributeRepository;
import cz.jenprochazim.rpg_game.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationAttributeServiceImpl implements LocationAttributeService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationAttributeRepository locationAttributeRepository;

    @Override
    public void createAttribute(LocationAttributeDTO locationAttributeDTO, Integer p, Integer r) {
        LocationAttributeEntity newLocationAttribute = new LocationAttributeEntity();
        newLocationAttribute.setName(locationAttributeDTO.getName());
        newLocationAttribute.setLocation(locationRepository.findByPAndR(p, r).orElseThrow());
        locationAttributeRepository.save(newLocationAttribute);
    }
}
