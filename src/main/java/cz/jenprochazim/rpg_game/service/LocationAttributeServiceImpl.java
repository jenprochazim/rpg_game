package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationAttributeDTO;
import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.entity.LocationAttributeEntity;
import cz.jenprochazim.rpg_game.entity.LocationEntity;
import cz.jenprochazim.rpg_game.mapper.LocationAttributeMapper;
import cz.jenprochazim.rpg_game.mapper.LocationMapper;
import cz.jenprochazim.rpg_game.repository.LocationAttributeRepository;
import cz.jenprochazim.rpg_game.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationAttributeServiceImpl implements LocationAttributeService {
    @Autowired
    LocationAttributeMapper locationAttributeMapper;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationAttributeRepository locationAttributeRepository;

    @Override
    public void createAttribute(LocationAttributeDTO locationAttributeDTO, Integer p, Integer r) {
        LocationAttributeEntity newLocationAttribute = new LocationAttributeEntity();
        newLocationAttribute.setName(locationAttributeDTO.getName());
        newLocationAttribute.setLocation(locationRepository.findByPAndR(p, r).orElseThrow());
        boolean exists = locationAttributeRepository.existsByNameAndLocation(newLocationAttribute.getName(), newLocationAttribute.getLocation());
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tento atribut už na lokaci existuje.");
        }
        else {
            locationAttributeRepository.save(newLocationAttribute);
        }
    }

    @Override
    public List<LocationAttributeDTO> getAttributesByLocation(Integer p, Integer r) {
        LocationEntity location = locationRepository.findByPAndR(p, r).orElseThrow();
        return locationAttributeRepository.findByLocation(location).stream()
                .map(attribute -> locationAttributeMapper.toLocationAttributeDTO(attribute))
                .toList();
    }

    @Override
    public void deleteAttribute(Integer attributeID) {
        LocationAttributeEntity locationAttribute = locationAttributeRepository.findById(Long.valueOf(attributeID)).orElseThrow();
        locationAttributeRepository.delete(locationAttribute);
    }

    @Override
    public List<LocationAttributeDTO> getAllAttributes() {
        return locationAttributeRepository.findAll().stream()
                .map(location -> locationAttributeMapper.toLocationAttributeDTO(location))
                .toList();
    }
}
