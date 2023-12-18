package com.yorastd.projectmanagement.Services.TaskServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskNode;
import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskTree;
import com.yorastd.projectmanagement.Repositories.Tasks.TaskNodeRepo;
import com.yorastd.projectmanagement.Repositories.Tasks.TaskRepo;
import com.yorastd.projectmanagement.Repositories.Tasks.TaskTreeRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskTreeService {
    public static final int DAY_IN_MILLISEC = 86400000;
    private final TaskTreeRepo taskTreeRepo;

    private final TaskNodeService taskNodeService;
    private final TaskService taskService;

    private final TaskRepo taskRepo;
    private final TaskNodeRepo taskNodeRepo;

    @Transactional
    public TaskTree createTaskTree(ArrayList<Task> tasks) {
        TaskTree taskTree = new TaskTree();
        taskTree.setRoots(new ArrayList<>());

        // Identify root nodes and create task nodes
        List<TaskNode> taskNodes = new ArrayList<>();
        for (Task task : tasks) {
            TaskNode taskNode = new TaskNode();
            taskNode.setId(UUID.randomUUID().hashCode());
            taskNode.setTask(task);
            taskNode.setChildren(new ArrayList<>());
            taskNode.setPredecessors(new ArrayList<>());
            taskNodes.add(taskNode);

            // Check if the task has no predecessors
            if (task.getPredecessors().isEmpty()) {
                taskTree.getRoots().add(taskNode);
            }
        }

        System.out.println(taskNodes);

        // Link nodes based on predecessor relationships
        for (TaskNode taskNode : taskNodes) {
            Task task = taskNode.getTask();
            for (Task predecessorTask : taskService.getTasksPredecessors(task)) {
                for (TaskNode predecessorNode : taskNodes) {
                    if (predecessorNode.getTask().getId().equals(predecessorTask.getId())) {
                        taskNode.getPredecessors().add(predecessorNode.getId());
                        predecessorNode.getChildren().add(taskNode.getId());

                    }
                }
            }
        }

//        for (TaskNode taskNode : taskNodes) {
//            taskNode.setTaskTree(taskTree);
//            taskNodeRepo.save(taskNode);
//        }
//
//        // Save the task tree
//        taskTreeRepo.save(taskTree);

        return taskTree;
    }

    @Transactional
    public TaskTree calculateTaskTreeDatesSoon(TaskTree taskTree, Date startDate) {

        startDate = new Date(System.currentTimeMillis() + DAY_IN_MILLISEC); //Todo change this later

        // getting the roots
        ArrayList<TaskNode> roots = new ArrayList<TaskNode>(taskTree.getRoots());

        // Calculate the dates of the children
        for (TaskNode root : roots) {
            root = calculateTaskNodeDatesSoon(root, startDate);
        }

        // Save the task tree
        taskTreeRepo.save(taskTree);

        return taskTree;
    }

    @Transactional
    public TaskNode calculateTaskNodeDatesSoon(TaskNode taskNode, Date startDate) {
        // Task Node has predecessors
        ArrayList<TaskNode> predecessors = taskNodeService.getTaskNodePredecessors(taskNode);
//                new ArrayList<>(taskNode.getPredecessors());

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
            ArrayList<TaskNode> children = taskNodeService.getTaskNodeChildren(taskNode);
//                    new ArrayList<>(taskNode.getChildren());

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
            ArrayList<TaskNode> children = taskNodeService.getTaskNodeChildren(taskNode);
//                    new ArrayList<>(taskNode.getChildren());

            // Calculate the dates of the children
            for (TaskNode child : children) {
                child = calculateTaskNodeDatesSoon(child, taskNode.getTask().getEndDateSoon());
            }
        }

        return taskNode;
    }

    @Transactional
    public TaskTree calculateTaskTreeDatesLate(TaskTree taskTree, Date startDate) {
        startDate = new Date(System.currentTimeMillis() + DAY_IN_MILLISEC); //Todo change this later

        return taskTree;
    }

    @Transactional
    public TaskNode calculateTaskNodeDatesLate(TaskNode taskNode, Date startDate) {
        return taskNode;
    }

    public List<Task> createSampleTasks() {
        // Create some sample tasks
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("Task 1");
        task1.setTimeRequired(new Date(1000 * 60 * 60 * 24 * 2 ));
        task1.setPredecessors(new ArrayList<>());

        Task task2 = new Task();
        task2.setId(2);
        task2.setName("Task 2");
        task2.setPredecessors(new ArrayList<>());
        task2.setTimeRequired(new Date(1000 * 60 * 60 * 24 * 2 ));
        task2.getPredecessors().add(task1.getId());

        Task task3 = new Task();
        task3.setId(3);
        task3.setName("Task 3");
        task3.setTimeRequired(new Date(1000 * 60 * 60 * 24 * 2 ));
        task3.setPredecessors(new ArrayList<>());
        task3.getPredecessors().add(task1.getId());

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

//        taskRepo.saveAll(tasks);

        return tasks;
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
