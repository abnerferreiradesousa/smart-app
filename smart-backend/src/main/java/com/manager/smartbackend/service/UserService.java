package com.manager.smartbackend.service;

import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.domain.repository.UserRepository;
import com.manager.smartbackend.infra.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User userToCreate) {
        return this.userRepository.save(userToCreate);
    }

    public User login(User userToLogin) {
        User userExists = this.getUserByEmail(userToLogin.getEmail());

        if (!userExists.getPassword().equals(userToLogin.getPassword())) {
            throw new NotFoundException();
        }

        return userExists;
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

}
