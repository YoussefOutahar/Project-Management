package com.yorastd.projectmanagement.Models.TaskTreeModel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
public class TaskTree {
    @Id
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    @OneToMany
    private ArrayList<TaskNode> roots;
}
