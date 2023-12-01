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
    private Long id;

    @OneToOne
    private Project project;

    private String title = "Untitled Board";

    private Date createdAt = new Date();
}
