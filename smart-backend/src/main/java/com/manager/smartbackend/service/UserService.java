package com.manager.smartbackend.service;

import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.domain.repository.UserRepository;
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

}
