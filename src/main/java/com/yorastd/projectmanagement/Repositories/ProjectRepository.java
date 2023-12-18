package com.yorastd.projectmanagement.Repositories;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Trello.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
}
