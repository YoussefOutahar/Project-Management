package com.yorastd.projectmanagement.Models.Tasks;

import com.yorastd.projectmanagement.Models.Project;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer source;

    private Integer target;

    private String type;

    @ManyToOne
    private Project project;
}
