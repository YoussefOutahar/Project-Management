package com.yorastd.projectmanagement.Controllers.Tasks;

import com.yorastd.projectmanagement.Models.Tasks.Link;
import com.yorastd.projectmanagement.Services.TaskServices.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/{projectId}/links")
@AllArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/all")
    public ResponseEntity<List<Link>> getAllLinks(@PathVariable Integer projectId) {
        try {
            List<Link> links = linkService.getAllLinks(projectId);
            return ResponseEntity.ok(links);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Link> createLink(@PathVariable Integer projectId,@RequestBody Link link) {
        try {
            linkService.createLink(projectId,link);
            return ResponseEntity.ok(link);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get/{linkId}")
    public ResponseEntity<Link> getLink(@PathVariable Long linkId) {
        try {
            Link link = linkService.getLink(linkId);
            return ResponseEntity.ok(link);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Link> updateLink(@RequestBody Link link) {
        try {
            linkService.updateLink(link);
            return ResponseEntity.ok(link);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{linkId}")
    public ResponseEntity<Link> deleteLink(@PathVariable Long linkId) {
        try {
            linkService.deleteLink(linkId);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
