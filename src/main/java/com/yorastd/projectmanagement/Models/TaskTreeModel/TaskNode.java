package com.yorastd.projectmanagement.Models.TaskTreeModel;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorastd.projectmanagement.Models.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
public class TaskNode {
    @Id
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    @OneToOne
    private Task task;

    @Getter
    @Setter
    @OneToMany
    private ArrayList<TaskNode> children;

    @Getter
    @Setter
    @OneToMany
    @JsonIgnore
    private ArrayList<TaskNode> predecessors;
}
