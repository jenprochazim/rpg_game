package cz.jenprochazim.rpg_game.dto.locationDTO;

import cz.jenprochazim.rpg_game.dto.userDTO.UserDTO;
import cz.jenprochazim.rpg_game.entity.enums.TerrainType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationRadiusDTO {
    private TerrainType terrainType;
    private Integer centerR;
    private Integer centerP;
    private Integer radius;
    private Integer density;

}
