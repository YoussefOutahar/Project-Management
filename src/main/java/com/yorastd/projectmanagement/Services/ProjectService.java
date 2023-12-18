package com.yorastd.projectmanagement.Services;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Repositories.ProjectRepository;
import com.yorastd.projectmanagement.Repositories.Trello.BoardRepository;
import com.yorastd.projectmanagement.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final TrelloService trelloService;
    
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
        project.setTasks(updatedProject.getTasks());
        project.setCollaborators(updatedProject.getCollaborators());

        projectRepository.save(project);
        return project;
    }

    public List<Board> getAllProjectBoard(Integer id) {
        List<Board> boards = projectRepository.getProjectBoardsById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        return boards;
    }

    public Project addBoardToProject(Integer projectId,Integer boardId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new RuntimeException("Board not found")
        );
        trelloService.setBoardsToProject(projectId, List.of(board));
        projectRepository.save(project);
        return project;
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<Project> getAllByUserId(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        ).getProjects();
    }
}
