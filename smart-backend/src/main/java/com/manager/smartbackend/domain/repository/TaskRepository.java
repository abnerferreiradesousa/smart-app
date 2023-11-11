package com.manager.smartbackend.domain.repository;

import com.manager.smartbackend.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
