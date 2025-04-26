package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.UserDTO;
import cz.jenprochazim.rpg_game.dto.UserRegistrationDTO;
import cz.jenprochazim.rpg_game.dto.UserUpdateDTO;
import cz.jenprochazim.rpg_game.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(UserRegistrationDTO user);
    List <UserDTO> getAllUsers();
    UserDTO getUser(Long id);
    void deleteUser(Long id);
    UserDTO updateUser (UserUpdateDTO updatedUser, Long id);
    public UserEntity getUserEntity(Long id);
}
