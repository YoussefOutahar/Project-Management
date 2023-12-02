package com.yorastd.projectmanagement.Controllers;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Services.ProjectService;
import com.yorastd.projectmanagement.Services.TrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final TrelloService trelloService;

    @PutMapping("get/all")
    public ResponseEntity<List<Project>> getAll() {
        try {
            List<Project> projects = projectService.getAll();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("create")
    public ResponseEntity<Project> create() {
        System.out.println("Creating project");
        try {
            Project project = projectService.create();
            System.out.println("Project created");
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        try {
            projectService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Project> update(@PathVariable long id, @RequestParam Project updatedProject) {
        try {
            Project project = projectService.update(
                    id,
                    updatedProject
            );
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("getBoards/{id}")
    public ResponseEntity<List<Board>> getAllProjectBoards(@PathVariable long id) {
        try {
            List<Board> boards = projectService.getAllProjectBoard(id);
            return ResponseEntity.ok(boards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{projectId}/addBoard/{boardId}")
    public ResponseEntity<Project> addBoardToProject(@PathVariable long projectId, @PathVariable long boardId) {
        try {
            Project project = projectService.addBoardToProject(projectId, boardId);
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}
