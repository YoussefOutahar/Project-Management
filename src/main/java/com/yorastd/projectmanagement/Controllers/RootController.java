package com.yorastd.projectmanagement.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yorastd.projectmanagement.Models.Task;
import com.yorastd.projectmanagement.Models.TaskTreeModel.TaskNode;
import com.yorastd.projectmanagement.Models.TaskTreeModel.TaskTree;
import com.yorastd.projectmanagement.Services.TaskService;
import com.yorastd.projectmanagement.Services.TaskTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.*;

@RestController("/")
public class RootController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskTreeService taskTreeService;

    @GetMapping
    public String index() {
        return "Hello World!";
    }

    @GetMapping("/taskTreeJson")
    public String getTaskTreeJson() {
        // Create some sample tasks
        List<Task> tasks = createSampleTasks();

        // Build the task tree
        TaskTree taskTree = taskTreeService.createTaskTree(new ArrayList<>(tasks));

        // Convert the task tree to JSON
        return taskTreeService.convertTaskTreeToJson(taskTree);
    }

    @GetMapping("/taskTreeTime")
    public String Calculate() {
        // Create some sample tasks
        List<Task> tasks = createSampleTasks();

        // Build the task tree
        TaskTree taskTree = taskTreeService.createTaskTree(new ArrayList<>(tasks));

        // Calculate the time required for each task
        taskTree = taskTreeService.calculateTasksDatesSoon(taskTree, new Date(System.currentTimeMillis()));

        // Convert the task tree to JSON
        return taskTreeService.convertTaskTreeToJson(taskTree);
    }



    // Helper method to create sample tasks
    private List<Task> createSampleTasks() {
        // Create some sample tasks
        Task task1 = new Task();
        task1.setId(1L);
        task1.setName("Task 1");
        task1.setTimeRequired(new Date(1000 * 60 * 60 * 24 * 2 ));
        task1.setPredecessors(new ArrayList<>());

        Task task2 = new Task();
        task2.setId(2L);
        task2.setName("Task 2");
        task2.setPredecessors(new ArrayList<>());
        task2.setTimeRequired(new Date(1000 * 60 * 60 * 24 * 2 ));
        task2.getPredecessors().add(task1);

        Task task3 = new Task();
        task3.setId(3L);
        task3.setName("Task 3");
        task3.setTimeRequired(new Date(1000 * 60 * 60 * 24 * 2 ));
        task3.setPredecessors(new ArrayList<>());
        task3.getPredecessors().add(task1);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        return tasks;
    }
}
