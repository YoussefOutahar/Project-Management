package com.yorastd.projectmanagement.Repositories;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Tasks.Link;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query(
            value = """
                    select b from Project p inner join Board b
                    on p.id = b.project.id
                    where p.id = :id
                    """
    )
    Optional<List<Board>> getProjectBoardsById(Integer id);

    @Query(
            value = """
                    select u from Project p inner join User u
                    on p.id = u.projects.id
                    where p.id = :id
                    """
    )
    Optional<List<User>> getCollaboratorsById(Integer id);


    @Query(
            value = """
                    select t from Project p inner join Task t
                    on p.id = t.project.id
                    where p.id = :id
                    """)
    Optional<List<Task>> getProjectTasksById(Integer id);

    @Query(
            value = """
                    select l from Project p inner join Link l
                    on p.id = l.project.id
                    where p.id = :id
                    """
    )
    Optional<List<Link>> getProjectLinksById(Integer id);
}
