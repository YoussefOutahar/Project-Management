package com.yorastd.projectmanagement.Models.Tasks;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.User.User;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    private Integer parent;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;

    private int priority;
    
    private int duration;

    private String createdAt;

    private String start_date;

    private String end_date;

    private double progress;

    @ManyToMany
    @JsonIgnore
    private List<User> assignedTo;

    @ManyToOne
    @JsonIgnore
    private Project project;

}
