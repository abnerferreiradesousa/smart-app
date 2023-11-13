package com.manager.smartbackend.controller;

import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User userFounded = this.userService.getUserById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userFounded);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        User userCreated = this.userService.create(userToCreate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userCreated);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User userToLogin) {
        User userLogged = this.userService.login(userToLogin);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userLogged);
    }

    @PutMapping("{userId}")
    public ResponseEntity<User> update(@RequestBody User userToUpdate, @PathVariable String userId) {
        User userUpdated = this.userService.update(userToUpdate, userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userUpdated);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> remove(@PathVariable String userId) {
        this.userService.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
