package com.yorastd.projectmanagement.Services;

import com.yorastd.projectmanagement.Models.Budget.HumanResource;
import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Repositories.ProjectRepository;
import com.yorastd.projectmanagement.Repositories.Tasks.TaskRepo;
import com.yorastd.projectmanagement.Repositories.Trello.BoardRepository;
import com.yorastd.projectmanagement.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepo taskRepo;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Transactional
    public Project create(Project project) {
        projectRepository.save(project);
        return project;
    }

    @Transactional
    public void delete(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        projectRepository.delete(project);
    }

    @Transactional
    public Project update(Integer id, Project updatedProject) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        project.setName(updatedProject.getName());
        projectRepository.save(project);
        return project;
    }

    @Transactional
    public Double getBudgetRation(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        List<Task> tasks = projectRepository.getProjectTasksById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        List<HumanResource> humanResources = projectRepository.getHumanResourcesById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        Double budget = project.getBudget();
        Double totalBudget = 0.0;
        for (Task task : tasks) {
//            Get Start Date and End Date Strings from Task
            String startDateString = task.getStart_date();
            String endDateString = task.getEnd_date();

//            Convert Start Date and End Date Strings to Date Objects
            Date startDate = new Date(startDateString);
            Date endDate = new Date(endDateString);

//            Get Task Duration in Days
            long duration = endDate.getTime() - startDate.getTime();
            long diffInDays = (duration / (1000 * 60 * 60 * 24)) + 1;


        }

        return budget / totalBudget;
    }
}
