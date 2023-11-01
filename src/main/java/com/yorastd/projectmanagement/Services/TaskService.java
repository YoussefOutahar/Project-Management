package com.yorastd.projectmanagement.Services;

import com.yorastd.projectmanagement.Models.Task;
import com.yorastd.projectmanagement.Repositories.TaskRepo;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    public Iterable<Task> getAllTasks() {
        return taskRepo.findAll();
    }
}
