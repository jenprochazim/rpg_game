package cz.jenprochazim.rpg_game.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    Long id;
    String userName;
    String email;
}