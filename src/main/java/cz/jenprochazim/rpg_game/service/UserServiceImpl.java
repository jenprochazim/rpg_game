package cz.jenprochazim.rpg_game.service;

import cz.jenprochazim.rpg_game.entity.UserEntity;
import cz.jenprochazim.rpg_game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
