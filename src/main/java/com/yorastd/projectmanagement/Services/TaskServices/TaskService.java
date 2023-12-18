package com.yorastd.projectmanagement.Services.TaskServices;

import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Repositories.Tasks.TaskRepo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void createTask(Task task) {
        taskRepo.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getTask(Integer taskId) {
        return taskRepo.findById(taskId).orElse(null);
    }

    public void updateTask(Task task) {
        taskRepo.save(task);
    }

    public void deleteTask(Integer taskId) {
        taskRepo.deleteById(taskId);
    }

    public ArrayList<Task> getTasksPredecessors(Task task) {
        ArrayList<Task> predecessors = new ArrayList<Task>();
        for (Integer predecessorId : task.getPredecessors()) {
            predecessors.add(taskRepo.findById(predecessorId).orElse(null));
        }
        return predecessors;
    }
}
