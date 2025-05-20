package cz.jenprochazim.rpg_game.dto.locationDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationAttributeDTO {
    private Long id;
    private String name;
    private LocationDTO location;
}
