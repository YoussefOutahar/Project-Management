package com.yorastd.projectmanagement.Controllers;

import com.yorastd.projectmanagement.Models.Budget.HumanResource;
import com.yorastd.projectmanagement.Services.BudgetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/{projectId}/budget")
@AllArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    @GetMapping("/human-resources/get/all")
    public List<HumanResource> getAllHumanResources(
            @PathVariable Integer projectId
    ) {
        return budgetService.getAllHumanResources(projectId);
    }

    @PostMapping("/human-resources/create")
    public HumanResource addHumanResource(@PathVariable Integer projectId, @RequestBody HumanResource humanResource) {
        return budgetService.addHumanResource(projectId,humanResource);
    }

    @PutMapping("/human-resources/update")
    public HumanResource updateHumanResource(@PathVariable Integer projectId,@RequestBody HumanResource humanResource) {
        return budgetService.updateHumanResource(humanResource);
    }

    @DeleteMapping("/human-resources/delete/{id}")
    public void deleteHumanResource(@PathVariable Integer projectId,@PathVariable Integer id) {
        budgetService.deleteHumanResource(id);
    }
}
