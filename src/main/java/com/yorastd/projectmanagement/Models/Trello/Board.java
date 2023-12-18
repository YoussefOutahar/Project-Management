package com.yorastd.projectmanagement.Models.Trello;

import com.yorastd.projectmanagement.Models.Project;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Project project;

    private String title = "Untitled Board";

    private String description = "No description";

    private boolean isDone = false;

    private boolean isArchived = false;

    private Date createdAt = new Date();
}
