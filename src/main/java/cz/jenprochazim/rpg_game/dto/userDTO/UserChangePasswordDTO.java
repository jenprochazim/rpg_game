package cz.jenprochazim.rpg_game.dto.userDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordDTO {
    String currentPassword;
    String newPassword;
}
