package cz.jenprochazim.rpg_game.dto.userDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    String userName;
    String password;
}