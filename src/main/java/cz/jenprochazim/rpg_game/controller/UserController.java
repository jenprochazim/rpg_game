package cz.jenprochazim.rpg_game.controller;

import cz.jenprochazim.rpg_game.dto.userDTO.*;
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

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO user) {
        return ResponseEntity.ok(userService.logIn(user));
    }

    @PostMapping("/registration")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRegistrationDTO user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO updatedUser) {
        return ResponseEntity.ok(userService.updateUser(updatedUser, id));
    }

    @PutMapping("/{id}/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody UserChangePasswordDTO user, @PathVariable Long id) {
        userService.changePassword(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
