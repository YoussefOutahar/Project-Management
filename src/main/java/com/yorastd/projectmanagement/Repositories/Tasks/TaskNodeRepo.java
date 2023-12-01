package com.yorastd.projectmanagement.Repositories.Tasks;

import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskNodeRepo extends JpaRepository<TaskNode,Integer> {
}