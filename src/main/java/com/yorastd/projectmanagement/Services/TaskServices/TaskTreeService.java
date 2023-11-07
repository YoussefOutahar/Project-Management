package com.yorastd.projectmanagement.Services.TaskServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskNode;
import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskTree;
import com.yorastd.projectmanagement.Repositories.TaskNodeRepo;
import com.yorastd.projectmanagement.Repositories.TaskRepo;
import com.yorastd.projectmanagement.Repositories.TaskTreeRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskTreeService {
    public static final int DAY_IN_MILLISEC = 86400000;
    private final TaskTreeRepo taskTreeRepo;

    private final TaskRepo taskRepo;
    private final TaskNodeRepo taskNodeRepo;

    public TaskTreeService(TaskTreeRepo taskTreeRepo,TaskNodeRepo taskNodeRepo,TaskRepo taskRepo) {
        this.taskTreeRepo = taskTreeRepo;
        this.taskNodeRepo = taskNodeRepo;
        this.taskRepo = taskRepo;
    }

    @Transactional
    public TaskTree createTaskTree(ArrayList<Task> tasks) {
        TaskTree taskTree = new TaskTree();
        taskTree.setRoots(new ArrayList<>());

        // Identify root nodes and create task nodes
        List<TaskNode> taskNodes = new ArrayList<>();
        for (Task task : tasks) {
            TaskNode taskNode = new TaskNode();
            taskNode.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            taskNode.setTask(task);
            taskNode.setChildren(new ArrayList<>());
            taskNode.setPredecessors(new ArrayList<>());
            taskNodes.add(taskNode);

            // Check if the task has no predecessors
            if (task.getPredecessors().isEmpty()) {
                taskTree.getRoots().add(taskNode);
            }
        }

        // Link nodes based on predecessor relationships
        for (TaskNode taskNode : taskNodes) {
            Task task = taskNode.getTask();
            for (Task predecessorTask : task.getPredecessors()) {
                for (TaskNode predecessorNode : taskNodes) {
                    if (predecessorNode.getTask().getId().equals(predecessorTask.getId())) {
                        taskNode.getPredecessors().add(predecessorNode);
                        predecessorNode.getChildren().add(taskNode);
                    }
                }
            }
        }

        // Save the task tree
        // saveTaskTree(taskTree);

        return taskTree;
    }

    @Transactional
    public TaskTree calculateTreeDatesSoon(TaskTree taskTree, Date startDate) {

        startDate = new Date(System.currentTimeMillis() + DAY_IN_MILLISEC); //Todo change this later

        // getting the roots
        ArrayList<TaskNode> roots = new ArrayList<TaskNode>(taskTree.getRoots());

        // Calculate the dates of the children
        for (TaskNode root : roots) {
            root = calculateTaskNodeDatesSoon(root, root.getTask().getEndDateSoon());
        }

        //saveTaskTree(taskTree); TODO
        return taskTree;
    }

    @Transactional
    public TaskNode calculateTaskNodeDatesSoon(TaskNode taskNode, Date startDate) {
        // Task Node has predecessors
        ArrayList<TaskNode> predecessors = new ArrayList<>(taskNode.getPredecessors());

        if(!predecessors.isEmpty()){
            // Find the most recent end date of the predecessors
            Date mostRecentDate = predecessors.get(0).getTask().getEndDateSoon();
            for (TaskNode predecessor : predecessors) {
                if (predecessor.getTask().getEndDateSoon().after(mostRecentDate)) {
                    mostRecentDate = predecessor.getTask().getEndDateSoon();
                }
            }

            // Set the start date plus 1 day of the task node
            taskNode.getTask().setStartDateSoon(
                    new Date(mostRecentDate.getTime() + DAY_IN_MILLISEC)
            );

            // Set the end date of the task node
            taskNode.getTask().setEndDateSoon(
                    new Date(
                            mostRecentDate.getTime() + taskNode.getTask().getTimeRequired().getTime()
                    )
            );
            ArrayList<TaskNode> children = new ArrayList<>(taskNode.getChildren());

            // Calculate the dates of the children
            for (TaskNode child : children) {
                child = calculateTaskNodeDatesSoon(child, taskNode.getTask().getEndDateSoon());
            }
        } else {
            // Task Node has no predecessors
            taskNode.getTask().setStartDateSoon(startDate);
            taskNode.getTask().setEndDateSoon(
                    new Date(
                            startDate.getTime() + taskNode.getTask().getTimeRequired().getTime()
                    )
            );
            ArrayList<TaskNode> children = new ArrayList<>(taskNode.getChildren());

            // Calculate the dates of the children
            for (TaskNode child : children) {
                child = calculateTaskNodeDatesSoon(child, taskNode.getTask().getEndDateSoon());
            }
        }

        return taskNode;
    }

    @Transactional
    public TaskTree calculateTreeDatesLate() {
        return null;
    }

    @Transactional
    public TaskNode calculateTaskNodeDatesLate(TaskNode taskNode, Date startDate) {
        return null;
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
