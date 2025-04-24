package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.entity.UserEntity;
import java.util.List;

public interface UserService {
    void saveUser(UserEntity user);
    List <UserEntity> getAllUsers();
}
