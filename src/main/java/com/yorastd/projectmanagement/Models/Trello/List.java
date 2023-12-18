package com.yorastd.projectmanagement.Models.Trello;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Entity
@Data
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Board board;

    @Value("Untitled List")
    private String title;

    @Value("No description")
    private String description;

    @Value("open")
    private String status;

    private Date createdAt = new Date();
}
