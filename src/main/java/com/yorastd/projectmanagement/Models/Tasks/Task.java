package com.yorastd.projectmanagement.Models.Tasks;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskNode;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @OneToMany
    private List<Task> predecessors;

    @JsonIgnore
    @OneToOne
    private TaskNode taskNode;

    private String name;

    private String description;

    private String status;

    private int priority;
    
    private Date timeRequired;

    private String createdAt;

    private Date startDateSoon;

    private Date startDateLate;

    private Date endDateSoon;
    
    private Date endDateLate;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name=" + name +
//                ", priority=" + priority +
//                ", predecessors=" + predecessors +
//                ", timeRequired=" + timeRequired +
//                ", createdAt=" + createdAt +
//                ", startDateSoon=" + startDateSoon +
//                ", startDateLate=" + startDateLate +
//                ", endDateSoon=" + endDateSoon +
//                ", endDateLate=" + endDateLate +
                '}';
    }

}
