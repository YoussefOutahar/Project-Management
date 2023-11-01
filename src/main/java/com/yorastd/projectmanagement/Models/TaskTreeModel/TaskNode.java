package com.yorastd.projectmanagement.Models.TaskTreeModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorastd.projectmanagement.Models.Task;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class TaskNode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Task task;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TaskNode> children;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TaskNode> predecessors;

    @Override
    public String toString() {
        return "TaskNode{" +
                "id=" + id +
                ", task=" + task +
//                ", children=" + children +
                ", predecessors=" + predecessors +
                '}';
    }
}
