package cz.jenprochazim.rpg_game.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
   String userName;
   @Email(message = "Email má nesprávný formát")
   String email;
}