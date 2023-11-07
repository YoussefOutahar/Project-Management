package com.yorastd.projectmanagement.Repositories;

import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTreeRepo extends JpaRepository<TaskTree,Long> {
}
