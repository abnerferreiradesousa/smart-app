package com.manager.smartbackend.controller;

import com.manager.smartbackend.domain.entity.Task;
import com.manager.smartbackend.domain.entity.User;
import com.manager.smartbackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Task> tasksFounded = this.taskService.getTasks(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tasksFounded);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task taskToCreate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Task taskCreated = this.taskService.create(taskToCreate, username);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskCreated);
    }
}
