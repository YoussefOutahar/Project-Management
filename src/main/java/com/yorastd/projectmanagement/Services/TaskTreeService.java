package com.yorastd.projectmanagement.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yorastd.projectmanagement.Models.Task;
import com.yorastd.projectmanagement.Models.TaskTreeModel.TaskNode;
import com.yorastd.projectmanagement.Models.TaskTreeModel.TaskTree;
import com.yorastd.projectmanagement.Repositories.TaskNodeRepo;
import com.yorastd.projectmanagement.Repositories.TaskTreeRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskTreeService {
    private final TaskTreeRepo taskTreeRepo;

    private final TaskNodeRepo taskNodeRepo;

    public TaskTreeService(TaskTreeRepo taskTreeRepo,TaskNodeRepo taskNodeRepo) {
        this.taskTreeRepo = taskTreeRepo;
        this.taskNodeRepo = taskNodeRepo;
    }

    public TaskTree createTaskTree(ArrayList<Task> tasks) {
        TaskTree taskTree = new TaskTree();
        taskTree.setRoots(new ArrayList<>());

        // Create a mapping of task IDs to corresponding task nodes
        Map<Long, TaskNode> taskNodeMap = new HashMap<>();
        for (Task task : tasks) {
            TaskNode taskNode = new TaskNode();
            taskNode.setTask(task);
            taskNode.setChildren(new ArrayList<>());
            taskNode.setPredecessors(new ArrayList<>());
            taskNodeMap.put(task.getId(), taskNode);
        }

        // Link nodes based on predecessor relationships
        for (Task task : tasks) {
            TaskNode taskNode = taskNodeMap.get(task.getId());
            for (Task predecessorTask : task.getPredecessors()) {
                TaskNode predecessorNode = taskNodeMap.get(predecessorTask.getId());
                taskNode.getPredecessors().add(predecessorNode);
                predecessorNode.getChildren().add(taskNode);
            }
        }

        // Find root nodes (nodes with no predecessors)
        for (TaskNode taskNode : taskNodeMap.values()) {
            if (taskNode.getPredecessors().isEmpty()) {
                taskTree.getRoots().add(taskNode);
            }
        }

        return taskTree;
    }

    public String convertTaskTreeToJson(TaskTree taskTree) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(taskTree);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;  // Handle the exception according to your needs
        }
    }

}
