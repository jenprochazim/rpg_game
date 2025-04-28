package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.dto.*;
import cz.jenprochazim.rpg_game.entity.UserEntity;
import cz.jenprochazim.rpg_game.exceptions.UserNotFoundException;
import cz.jenprochazim.rpg_game.mapper.UserMapper;
import cz.jenprochazim.rpg_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO logIn(UserLoginDTO userLoginDTO) {
        UserEntity user = userRepository.findByUserName(userLoginDTO.getUserName()).orElse(null);
        if (user == null || !passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Spatne uzivatelske jmeno nebo heslo");
        }
        return userMapper.toUserDTO(user);
    }

    @Override
    public void createUser(UserRegistrationDTO userDTO) {
        UserEntity user = userMapper.fromUserRegistrationDTO(userDTO);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Přihlašovací jméno už existuje");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> userMapper.toUserDTO(user))
                .toList();
    }

    @Override
    public UserDTO getUser(Long id) {
        return userMapper.toUserDTO(getUserEntity(id));
    }

    @Override
    public UserEntity getUserEntity(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Uzivatel nenalezen");
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDTO updateUser(UserUpdateDTO updatedUser, Long id) {
        UserEntity user = getUserEntity(id);
        user.setUserName(updatedUser.getUserName());
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Přihlašovací jméno už existuje");
        }
        return userMapper.toUserDTO(user);
    }

    @Override
    public void changePassword(UserChangePasswordDTO updatedUser, Long id) {
        UserEntity user = getUserEntity(id);
        if (!passwordEncoder.matches(updatedUser.getCurrentPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Spatne heslo.");
        } else {
            System.out.println("Puvodni heslo zmeneno na: " + updatedUser.getNewPassword());
            String hashedPassword = passwordEncoder.encode(updatedUser.getNewPassword());
            user.setPassword(hashedPassword);
            userRepository.save(user);
        }
    }

}
