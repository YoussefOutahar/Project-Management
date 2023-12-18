package com.yorastd.projectmanagement.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Models.User.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name = "Untitled Project";

    private String description = "No description";

    private Double budget = 0.0;

    private Date startDate = new Date();

    private Date endDate = new Date();

    private boolean isCompleted = false;

    private Date createdAt = new Date();

    @OneToMany
    @JsonIgnore
    private List<Board> boards;

    @ManyToMany
    @JsonIgnore
    private List<User> collaborators;

    @OneToMany
    @JsonIgnore
    private List<Task> tasks;
}
