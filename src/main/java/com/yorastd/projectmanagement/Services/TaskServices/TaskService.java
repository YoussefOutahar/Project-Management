package com.yorastd.projectmanagement.Services.TaskServices;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Tasks.TaskComment;
import com.yorastd.projectmanagement.Repositories.ProjectRepository;
import com.yorastd.projectmanagement.Repositories.Tasks.TaskCommentRepository;
import com.yorastd.projectmanagement.Repositories.Tasks.TaskRepo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {


    private final ProjectRepository projectRepository;
    private final TaskRepo taskRepo;
    private final TaskCommentRepository taskCommentRepository;

    public void createTask(Integer projectId,Task task) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " does not exist"));
        task.setProject(project);
        taskRepo.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getTask(Long taskId) {
        return taskRepo.findById(taskId).orElse(null);
    }

    public void updateTask(Task task) {
        taskRepo.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }

    public void createTaskComment(Long taskId, TaskComment taskComment) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new IllegalStateException("Task with id " + taskId + " does not exist"));
        taskComment.setTask(task);
        taskCommentRepository.save(taskComment);
    }

    public List<TaskComment> getTaskComments(Long taskId) {
        return taskRepo.getTaskCommentsById(taskId).orElseThrow(() -> new IllegalStateException("Task with id " + taskId + " does not exist"));
    }
}
