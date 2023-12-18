package com.yorastd.projectmanagement.Services.TaskServices;

import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskNode;
import com.yorastd.projectmanagement.Repositories.Tasks.TaskNodeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskNodeService {
    private final TaskNodeRepo taskNodeRepo;

    public void save(TaskNode taskNode) {
        taskNodeRepo.save(taskNode);
    }

    public void delete(TaskNode taskNode) {
        taskNodeRepo.delete(taskNode);
    }

    public ArrayList<TaskNode> getTaskNodeChildren(TaskNode taskNode) {
        ArrayList<TaskNode> children = new ArrayList<TaskNode>();
        for (Integer childId : taskNode.getChildren()) {
            children.add(taskNodeRepo.findById(childId).orElse(null));
        }
        return children;
    }

    public ArrayList<TaskNode> getTaskNodePredecessors(TaskNode taskNode) {
        ArrayList<TaskNode> predecessors = new ArrayList<TaskNode>();
        for (Integer predecessorId : taskNode.getPredecessors()) {
            predecessors.add(taskNodeRepo.findById(predecessorId).orElse(null));
        }
        return predecessors;
    }
}
