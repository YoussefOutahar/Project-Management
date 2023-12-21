package com.yorastd.projectmanagement.Models.Tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorastd.projectmanagement.Models.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class TaskComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text="";

    private Date createdAt = new Date();

    private String username;

    private Integer commentatorId;

    private String commentatorRole;

    @ManyToOne
    private Task task;

    @ManyToOne
    @JsonIgnore
    private User user;
}
