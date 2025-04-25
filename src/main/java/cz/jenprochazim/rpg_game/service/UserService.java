package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.UserRegistrationDTO;
import cz.jenprochazim.rpg_game.entity.UserEntity;
import java.util.List;

public interface UserService {
    void createUser(UserRegistrationDTO user);
    List <UserEntity> getAllUsers();
}
