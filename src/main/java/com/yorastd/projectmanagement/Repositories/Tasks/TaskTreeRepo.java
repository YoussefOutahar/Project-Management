package com.yorastd.projectmanagement.Repositories.Tasks;

import com.yorastd.projectmanagement.Models.Tasks.TaskTreeModel.TaskTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTreeRepo extends JpaRepository<TaskTree,Integer> {
}
