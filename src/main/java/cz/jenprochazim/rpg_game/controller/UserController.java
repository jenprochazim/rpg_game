package cz.jenprochazim.rpg_game.controller;

import cz.jenprochazim.rpg_game.dto.UserRegistrationDTO;
import cz.jenprochazim.rpg_game.entity.UserEntity;
import cz.jenprochazim.rpg_game.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cz.jenprochazim.rpg_game.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRegistrationDTO user) {

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }
}
