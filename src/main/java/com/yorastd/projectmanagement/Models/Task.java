package com.yorastd.projectmanagement.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Task {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private int priority;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany
    private ArrayList<Task> predecessors;

    @Getter
    @Setter
    private Date timeRequired;

    @Getter
    @Setter
    private String createdAt;

    @Getter
    @Setter
    private Date startDateSoon;

    @Getter
    @Setter
    private Date startDateLate;

    @Getter
    @Setter
    private Date endDateSoon;

    @Getter
    @Setter
    private Date endDateLate;
}
