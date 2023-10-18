package com.yorastd.projectmanagement;
import com.yorastd.projectmanagement.Models.Task;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestTreeCreatorTest {
    @Test
    void createTaskTree() {
//        // Create a list of tasks for testing
//        List<Task> tasks = new ArrayList<>();
//
//        // Create some tasks
//        Task task1 = new Task();
//        task1.setId(1L);
//        task1.setName("Task 1");
//        task1.setPredecessors(new ArrayList<>());
//
//        Task task2 = new Task();
//        task2.setId(2L);
//        task2.setName("Task 2");
//        task2.setPredecessors(new ArrayList<>());
//
//        Task task3 = new Task();
//        task3.setId(3L);
//        task3.setName("Task 3");
//        task3.setPredecessors(new ArrayList<>());
//
//        // Set task2 as a predecessor of task1
//        task1.getPredecessors().add(task2);
//
//        // Set task3 as a predecessor of task2
//        task2.getPredecessors().add(task3);
//
//        tasks.add(task1);
//        tasks.add(task2);
//        tasks.add(task3);
//
//        // Create an instance of TaskTreeCreator
//        TaskTreeCreator treeCreator = new TaskTreeCreator();
//
//        // Create the tree model
//        DefaultTreeModel treeModel = treeCreator.createTaskTree(tasks);
//
//        // Verify the structure of the tree
//        assertEquals(1, treeModel.getChildCount(treeModel.getRoot()));
//
//        // Check the first level of the tree (root should have one child)
//        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
//        DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) root.getChildAt(0);
//        Task rootNodeTask = (Task) root.getUserObject();
//        Task childNodeTask = (Task) childNode.getUserObject();
//
//        assertEquals("Task Tree", rootNodeTask.getName());
//        assertEquals("Task 1", childNodeTask.getName());
//
//        // Check the second level of the tree
//        assertEquals(1, childNode.getChildCount());
//        DefaultMutableTreeNode grandchildNode = (DefaultMutableTreeNode) childNode.getChildAt(0);
//        Task grandchildNodeTask = (Task) grandchildNode.getUserObject();
//
//        assertEquals("Task 2", grandchildNodeTask.getName());
//
//        // Add more assertions to verify the tree structure based on your specific scenario
    }
}
