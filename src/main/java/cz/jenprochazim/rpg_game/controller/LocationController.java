package cz.jenprochazim.rpg_game.controller;

import cz.jenprochazim.rpg_game.dto.locationDTO.LocationDTO;
import cz.jenprochazim.rpg_game.service.LocationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping
    public ResponseEntity<Void> createLocation(@RequestBody LocationDTO newLocation) {
        locationService.createLocation(newLocation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("actual/{p}/{r}")
    public ResponseEntity<LocationDTO> getActualLocation(@PathVariable Integer p, @PathVariable Integer r){
        return ResponseEntity.ok(locationService.getActualLocation(p,r));
    }

    @GetMapping("near/{p}/{r}")
    public ResponseEntity<List<LocationDTO>> getNearLocation(@PathVariable Integer p, @PathVariable Integer r){
        return ResponseEntity.ok(locationService.getNearLocation(p,r));
    }

    @GetMapping("north/{p}/{r}")
    public ResponseEntity<List<LocationDTO>> getNorthLocation(@PathVariable Integer p, @PathVariable Integer r){
        return ResponseEntity.ok(locationService.getNorthLocation(p,r));
    }
    @GetMapping("south/{p}/{r}")
    public ResponseEntity<List<LocationDTO>> getSouthLocation(@PathVariable Integer p, @PathVariable Integer r){
        return ResponseEntity.ok(locationService.getSouthLocation(p,r));
    }

    @GetMapping("east/{p}/{r}")
    public ResponseEntity<List<LocationDTO>> getEastLocation(@PathVariable Integer p, @PathVariable Integer r){
        return ResponseEntity.ok(locationService.getEastLocation(p,r));
    }

    @GetMapping("west/{p}/{r}")
    public ResponseEntity<List<LocationDTO>> getWestLocation(@PathVariable Integer p, @PathVariable Integer r){
        return ResponseEntity.ok(locationService.getWestLocation(p,r));
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @PutMapping
    public ResponseEntity<Void> updateLocation(@RequestBody LocationDTO updatedLocation) {
        locationService.updateLocation(updatedLocation);
        return ResponseEntity.ok().build();
    }
}
