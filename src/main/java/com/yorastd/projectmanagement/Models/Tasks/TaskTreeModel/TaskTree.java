package com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TaskTree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<TaskNode> roots;

    @Override
    public String toString() {
        return "TaskTree{" +
                "id=" + id +
                ", roots=" + roots +
                '}';
    }
}
