package com.yorastd.projectmanagement.Controllers;

import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskTree;
import com.yorastd.projectmanagement.Services.TaskServices.TaskService;
import com.yorastd.projectmanagement.Services.TaskServices.TaskTreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController("/api/v1/taskTree")
@RequiredArgsConstructor
public class TaskTreeController {

    private final TaskService taskService;
    private final TaskTreeService taskTreeService;

    @GetMapping("/taskTreeJson")
    public String getTaskTreeJson() {
        // Create some sample tasks
        List<Task> tasks = taskTreeService.createSampleTasks();

        // Build the task tree
        TaskTree taskTree = taskTreeService.createTaskTree(new ArrayList<>(tasks));

        // Convert the task tree to JSON
        return taskTreeService.convertTaskTreeToJson(taskTree);
    }

    @GetMapping("/taskTreeTime")
    public String Calculate() {
        // Create some sample tasks
        List<Task> tasks = taskTreeService.createSampleTasks();

        System.out.println("Tasks: " + tasks);

        // Build the task tree
        TaskTree taskTree = taskTreeService.createTaskTree(new ArrayList<>(tasks));

        System.out.println("Task Tree: " + taskTree);

        // Calculate the time required for each task
        taskTree = taskTreeService.calculateTaskTreeDatesSoon(taskTree, new Date(System.currentTimeMillis()));

        taskTree = taskTreeService.calculateTaskTreeDatesLate(taskTree, new Date(System.currentTimeMillis()));

        // Convert the task tree to JSON
        return taskTreeService.convertTaskTreeToJson(taskTree);
    }
}
