package com.yorastd.projectmanagement.Services.TaskServices;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Tasks.Link;
import com.yorastd.projectmanagement.Repositories.ProjectRepository;
import com.yorastd.projectmanagement.Repositories.Tasks.LinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;
    private final ProjectRepository projectRepository;

    public List<Link> getAllLinks(Integer projectId) {
        return projectRepository.getProjectLinksById(projectId).orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " does not exist"));
    }

    public void createLink(Integer projectId,Link link) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalStateException("Project with id " + projectId + " does not exist"));
        link.setProject(project);
        linkRepository.save(link);
    }

    public Link getLink(Long linkId) {
        return linkRepository.findById(linkId).orElse(null);
    }

    public void updateLink(Link link) {
        linkRepository.save(link);
    }

    public void deleteLink(Long linkId) {
        linkRepository.deleteById(linkId);
    }

}
