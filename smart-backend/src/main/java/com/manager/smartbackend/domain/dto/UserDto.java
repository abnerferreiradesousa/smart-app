package com.manager.smartbackend.domain.dto;

import com.manager.smartbackend.domain.entity.User;

public record UserDto(String name, String email, String password) {
    public User toEntity() {
        return User.builder().name(name).email(email).password(password).build();
    }
}
