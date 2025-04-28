package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.*;
import cz.jenprochazim.rpg_game.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(UserRegistrationDTO user);
    List <UserDTO> getAllUsers();
    UserDTO getUser(Long id);
    void deleteUser(Long id);
    UserDTO updateUser (UserUpdateDTO updatedUser, Long id);
    UserEntity getUserEntity(Long id);
    UserDTO logIn(UserLoginDTO userLoginDTO);
    void changePassword(UserChangePasswordDTO user, Long id);
}
