package com.yorastd.projectmanagement.Services;

import com.yorastd.projectmanagement.Models.Task;
import com.yorastd.projectmanagement.Repositories.TaskRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;


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

    // Create Task Tree
    public DefaultTreeModel createTaskTree(List<Task> tasks) {
        // Create the root node for the tree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(tasks.get(0));

        // Create a mapping of task IDs to their respective tree nodes
        List<DefaultMutableTreeNode> nodeMapping = new ArrayList<>();
        for (Task task : tasks) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(task);
            nodeMapping.add(node);
        }

        // Build the tree structure
        for (Task task : tasks) {
            DefaultMutableTreeNode parentNode = nodeMapping.get(tasks.indexOf(task));

            for (Task predecessor : task.getPredecessors()) {
                DefaultMutableTreeNode childNode = nodeMapping.get(tasks.indexOf(predecessor));
                parentNode.add(childNode);
            }

            // If the task has no predecessors, add it to the root
            if (task.getPredecessors().isEmpty()) {
                root.add(parentNode);
            }
        }

        // Create the tree model
        return new DefaultTreeModel(root);
    }
}
