package cz.jenprochazim.rpg_game.dto.userDTO;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    String userName;
    String password;
    @Email(message = "Email má nesprávný formát")
    String email;
}
