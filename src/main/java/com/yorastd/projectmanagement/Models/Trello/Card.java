package com.yorastd.projectmanagement.Models.Trello;

import jakarta.persistence.*;
import lombok.Data;
import com.yorastd.projectmanagement.Models.User.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Entity
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Board board;

    @OneToOne
    private List list;

    private String title = "Untitled Card";

    private String description = "No description";

    private int position;

    @OneToOne
    private User assignedTo;

    private boolean isDone = false;

    private boolean isArchived = false;

    private Date createdAt = new Date();
}
