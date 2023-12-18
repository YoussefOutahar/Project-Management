package com.yorastd.projectmanagement.Controllers;

import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Services.TaskServices.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/{projectId}/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable Integer projectId) {
        try {
            List<Task> tasks = taskService.getAllTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@PathVariable Integer projectId, @RequestBody Task task) {
        try {
            taskService.createTask(task);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Integer projectId, @PathVariable Integer taskId) {
        try {
            Task task = taskService.getTask(taskId);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer projectId, @PathVariable Integer taskId, @RequestBody Task task) {
        try {
            taskService.updateTask(task);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable Integer projectId, @PathVariable Integer taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}