package com.yorastd.projectmanagement.Models;

import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.User.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name = "Untitled Project";

    @OneToMany
    private List<User> collaborators;

    @OneToMany
    private List<Task> tasks;
}
