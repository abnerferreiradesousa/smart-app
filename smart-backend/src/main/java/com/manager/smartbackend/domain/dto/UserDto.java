package com.manager.smartbackend.domain.dto;

import com.manager.smartbackend.domain.entity.User;

public record UserDto(long id, String name, String email, String password, String token) {
    public User toEntity() {
        return User.builder()
                .name(name).email(email).password(password)
                .build();
    }
}
