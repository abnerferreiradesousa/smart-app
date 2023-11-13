package com.manager.smartbackend.controller;

import com.manager.smartbackend.domain.dto.AuthenticationDto;
import com.manager.smartbackend.domain.dto.UserDto;
import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.service.TokenService;
import com.manager.smartbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        UserDetails userDetails = userService.loadUserByUsername(userDto.name());

        if(userDetails != null) {
            String response = "Não foi possível cadastrar, nome de usuário já existe!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = userService.create(userDto.toEntity());
        String response = "Pessoa cadastrada com sucesso!";
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationDto authenticationDTO){
        System.out.println(authenticationDTO.name() + authenticationDTO.password() + "checou");
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(authenticationDTO.name(), authenticationDTO.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken(user);

        String response = token + " Pessoa autenticada com sucesso! " + auth.getName();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}