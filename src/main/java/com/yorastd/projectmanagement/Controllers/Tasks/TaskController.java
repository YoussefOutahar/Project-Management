package com.yorastd.projectmanagement.Controllers.Tasks;

import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Tasks.TaskComment;
import com.yorastd.projectmanagement.Models.User.User;
import com.yorastd.projectmanagement.Services.TaskServices.TaskService;
import com.yorastd.projectmanagement.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/{projectId}/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

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
            taskService.createTask(projectId,task);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        try {
            Task task = taskService.getTask(taskId);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer taskId, @RequestBody Task task) {
        try {
            taskService.updateTask(task);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Integer userId) {
        try {
            List<Task> tasks = taskService.getTasksByUserId(userId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/assign/{taskId}/{userId}")
    public ResponseEntity<Task> assignTask(@PathVariable Long taskId, @PathVariable Integer userId) {
        try {
            Task task = taskService.getTask(taskId);
            User user = userService.getUser(userId);
            task.getAssignedTo().add(user);
            taskService.updateTask(task);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/unassign/{taskId}/{userId}")
    public ResponseEntity<Task> unassignTask(@PathVariable Long taskId, @PathVariable Integer userId) {
        try {
            Task task = taskService.getTask(taskId);
            User user = userService.getUser(userId);
            task.getAssignedTo().remove(user);
            taskService.updateTask(task);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/comment/create/{taskId}")
    public ResponseEntity<TaskComment> createTaskComment(@PathVariable Long taskId, @RequestBody TaskComment taskComment) {
        try {
            System.out.println(taskComment);
            taskService.createTaskComment(taskId,taskComment);
            return ResponseEntity.ok(taskComment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/comment/all/{taskId}")
    public ResponseEntity<List<TaskComment>> getTaskComments(@PathVariable Long taskId) {
        try {
            List<TaskComment> taskComments = taskService.getTaskComments(taskId);
            return ResponseEntity.ok(taskComments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}
