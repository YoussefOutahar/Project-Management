package com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class TaskNode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(mappedBy = "taskNode",cascade = CascadeType.ALL)
    private Task task;

    @ManyToOne
    @JsonIgnore
    private TaskTree taskTree;

    @ElementCollection
    private List<Integer> children;

    @ElementCollection
    private List<Integer> predecessors;
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<TaskNode> children;
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<TaskNode> predecessors;

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
