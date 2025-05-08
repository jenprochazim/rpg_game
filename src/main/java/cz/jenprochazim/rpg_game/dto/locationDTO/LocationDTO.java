package cz.jenprochazim.rpg_game.dto.locationDTO;

import cz.jenprochazim.rpg_game.entity.UserEntity;
import cz.jenprochazim.rpg_game.entity.enums.TerrainType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private String name;
    private String description;
    private TerrainType terrainType;
    private UserEntity creator;
    private String note;
    private Integer r;
    private Integer p;
}

