package com.manager.smartbackend.controller;

import com.manager.smartbackend.domain.dto.AuthenticationDto;
import com.manager.smartbackend.domain.dto.UserDto;
import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.infra.exception.AlreadyExistsException;
import com.manager.smartbackend.service.TokenService;
import com.manager.smartbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
        User userDetails = (User) this.userService.loadUserByUsername(userDto.email());

        if(userDetails != null && userDetails.getEmail().equals(userDto.email())) {
            throw new AlreadyExistsException("User already exists!");
        }

        User user = userService.create(userDto.toEntity());
        String token = tokenService.generateToken(user);
        UserDto responseUserDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), token);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody AuthenticationDto authenticationDTO){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken(user);
        UserDto responseUserDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), token);
        return ResponseEntity.status(HttpStatus.OK).body(responseUserDto);
    }

}