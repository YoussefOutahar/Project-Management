package com.yorastd.projectmanagement.Repositories.Tasks;

import com.yorastd.projectmanagement.Models.Tasks.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment,Integer> {
}
