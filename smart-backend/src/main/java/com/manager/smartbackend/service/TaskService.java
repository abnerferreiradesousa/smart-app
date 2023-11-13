package com.manager.smartbackend.service;

import com.manager.smartbackend.domain.entity.Task;
import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.domain.repository.TaskRepository;
import com.manager.smartbackend.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task create(Task taskToCreate, String username) {
        User user = (User) this.userRepository.findByName(username);
        taskToCreate.setUser(user);
        return this.taskRepository.save(taskToCreate);
    }

    public List<Task> getTasks(String username) {
        User user = (User) this.userRepository.findByName(username);
        return user.getTasks();
    }

}
