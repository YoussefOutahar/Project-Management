package com.yorastd.projectmanagement.Services;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Repositories.ProjectRepository;
import com.yorastd.projectmanagement.Repositories.Trello.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BoardRepository boardRepository;

    private final TrelloService trelloService;

    @Transactional
    public Project create() {
        Project project = new Project();
        System.out.println("Project Instance Created");
        projectRepository.save(project);
        return project;
    }

    @Transactional
    public void delete(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        projectRepository.delete(project);
    }

    @Transactional
    public Project update(long id, Project updatedProject) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );

        project.setName(updatedProject.getName());
        project.setTasks(updatedProject.getTasks());
        project.setCollaborators(updatedProject.getCollaborators());

        projectRepository.save(project);
        return project;
    }

    public List<Board> getAllProjectBoard(Long id) {
        List<Board> boards = projectRepository.getProjectBoardsById(id).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        return boards;
    }

    public Project addBoardToProject(long projectId,long boardId) {
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
}
