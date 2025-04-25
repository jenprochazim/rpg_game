package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.UserDTO;
import cz.jenprochazim.rpg_game.dto.UserRegistrationDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(UserRegistrationDTO user);
    List <UserDTO> getAllUsers();
    UserDTO getUser(Long id);
}
