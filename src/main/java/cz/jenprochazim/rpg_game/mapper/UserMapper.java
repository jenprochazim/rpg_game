package cz.jenprochazim.rpg_game.mapper;

import cz.jenprochazim.rpg_game.dto.userDTO.UserDTO;
import cz.jenprochazim.rpg_game.dto.userDTO.UserLoginDTO;
import cz.jenprochazim.rpg_game.dto.userDTO.UserRegistrationDTO;
import cz.jenprochazim.rpg_game.dto.userDTO.UserUpdateDTO;
import cz.jenprochazim.rpg_game.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity fromUserRegistrationDTO (UserRegistrationDTO userRegistrationDTO);
    UserEntity fromUserLoginDTO(UserLoginDTO userLoginDTO);
    UserEntity fromUserUpdateDTO(UserUpdateDTO userUpdateDTO);
    UserEntity fromUserDTO(UserDTO userDTO);
    UserDTO toUserDTO(UserEntity userEntity);
}
