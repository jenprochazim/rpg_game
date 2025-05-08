package cz.jenprochazim.rpg_game.controller;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping("/createLocation")
    public ResponseEntity<Void> createLocation(@RequestBody LocationDTO newLocation) {
        locationService.createLocation(newLocation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
