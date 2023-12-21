package com.yorastd.projectmanagement.Models.Budget;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class HumanResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name = "Untitled Human Ressource";

    private String description = "No description";

    private Double costPerHour = 0.0;

    @OneToOne
    private User user;

    @ManyToOne
    private Project project;

    @OneToMany
    private List<Task> tasks;
}
