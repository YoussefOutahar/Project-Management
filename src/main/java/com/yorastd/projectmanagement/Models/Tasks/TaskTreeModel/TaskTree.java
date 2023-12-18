package com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class TaskTree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "taskTree", cascade = CascadeType.ALL)
    private List<TaskNode> roots;

    private boolean lateDatesCalculated;

//    public Date getStartDate(){
//        return
//    }
//
//    public Date getEndDateSoon() {
//        return
//    }

//    public Date getEndDateLate() {
//        return
//    }

    @Override
    public String toString() {
        return "TaskTree{" +
                "id=" + id +
                ", roots=" + roots +
                '}';
    }
}
