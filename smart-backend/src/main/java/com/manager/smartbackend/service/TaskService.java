package com.manager.smartbackend.service;

import com.manager.smartbackend.domain.entity.Task;
import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.domain.repository.TaskRepository;
import com.manager.smartbackend.domain.repository.UserRepository;
import com.manager.smartbackend.infra.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getTasks(String username) {
        User user = (User) this.userRepository.findByName(username);
        return user.getTasks();
    }

    public Task create(Task taskToCreate, String username) {
        User user = (User) this.userRepository.findByName(username);
        taskToCreate.setUser(user);
        return this.taskRepository.save(taskToCreate);
    }

    public Task update(Task taskToUpdate) {
        Task task = this.findById(String.valueOf(taskToUpdate.getId()));
        task.setTitle(taskToUpdate.getTitle());
        task.setStatus(taskToUpdate.getStatus());
        task.setDescription(taskToUpdate.getDescription());
        return this.taskRepository.save(task);
    }

    public void remove(String taskId) {
        Task task = this.findById(taskId);
        this.taskRepository.delete(task);
    }

    public Task findById(String taskId) {
        return this.taskRepository.findById(Long.valueOf(taskId))
                .orElseThrow(() -> new NotFoundException("Task Not Found!"));
    }
}
