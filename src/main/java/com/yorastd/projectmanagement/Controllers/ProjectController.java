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

    @GetMapping("get/all")
    public ResponseEntity<List<Project>> getAll() {
        try {
            List<Project> projects = projectService.getAll();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        try {
            projectService.create(project);
            System.out.println("Project Instance Saved");
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("get/user/{id}")
    public ResponseEntity<List<Project>> getAllByUserId(@PathVariable Integer id) {
        try {
            List<Project> projects = projectService.getAllByUserId(id);
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }



    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            projectService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Project> update(@PathVariable Integer id, @RequestParam Project updatedProject) {
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

    @GetMapping("get/boards/{id}")
    public ResponseEntity<List<Board>> getAllProjectBoards(@PathVariable Integer id) {
        try {
            List<Board> boards = projectService.getAllProjectBoard(id);
            return ResponseEntity.ok(boards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{projectId}/addBoard/{boardId}")
    public ResponseEntity<Project> addBoardToProject(@PathVariable Integer projectId, @PathVariable Integer boardId) {
        try {
            Project project = projectService.addBoardToProject(projectId, boardId);
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
