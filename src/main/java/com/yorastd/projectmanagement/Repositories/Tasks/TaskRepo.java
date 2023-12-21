package com.yorastd.projectmanagement.Repositories.Tasks;

import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Tasks.TaskComment;
import com.yorastd.projectmanagement.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query(
            value = """
                    select tc from Task t inner join TaskComment tc
                    on t.id = tc.task.id
                    where t.id = :id
                    """
    )
    Optional<List<TaskComment>> getTaskCommentsById(Long id);
}
