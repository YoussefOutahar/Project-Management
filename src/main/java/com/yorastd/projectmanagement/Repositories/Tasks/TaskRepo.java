package com.yorastd.projectmanagement.Repositories.Tasks;

import com.yorastd.projectmanagement.Models.Tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
}
