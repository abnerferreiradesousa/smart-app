package com.manager.smartbackend.domain.repository;

import com.manager.smartbackend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
