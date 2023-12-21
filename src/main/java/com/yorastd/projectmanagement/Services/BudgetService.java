package com.yorastd.projectmanagement.Services;

import com.yorastd.projectmanagement.Models.Budget.HumanResource;
import com.yorastd.projectmanagement.Models.User.User;
import com.yorastd.projectmanagement.Repositories.Budget.HumanResourceRepository;
import com.yorastd.projectmanagement.Repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BudgetService {

    private final HumanResourceRepository humanResourceRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public List<HumanResource> getAllHumanResources(Integer projectId) {
        return projectRepository.getHumanResourcesById(projectId).orElseThrow(
                () -> new IllegalStateException("Project with id " + projectId + " does not exist")
        );
    }

    @Transactional
    public HumanResource addHumanResource(Integer projectId,HumanResource humanResource) {
        humanResource.setProject(projectRepository.findById(projectId).orElseThrow(
                () -> new IllegalStateException("Project with id " + projectId + " does not exist")
        ));
        return humanResourceRepository.save(humanResource);
    }

    @Transactional
    public HumanResource updateHumanResource(HumanResource humanResource) {
        return humanResourceRepository.save(humanResource);
    }

    @Transactional
    public void deleteHumanResource(Integer id) {
        humanResourceRepository.deleteById(id);
    }
}
