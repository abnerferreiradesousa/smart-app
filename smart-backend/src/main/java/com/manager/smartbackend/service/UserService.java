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

    public User getUserById(String userId) {
        return this.userRepository.findById(Long.valueOf(userId))
                .orElseThrow(NotFoundException::new);
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

    public User update(User userToUpdate, String userId) {
        User userExists = this.getUserById(userId);

        userExists.setEmail(userToUpdate.getEmail());
        userExists.setName(userToUpdate.getName());
        userExists.setPassword(userToUpdate.getPassword());

        return userExists;
    }

    public void remove(String userId) {
        this.userRepository.deleteById(Long.valueOf(userId));
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
    }
}
