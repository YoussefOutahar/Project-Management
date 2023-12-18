package com.yorastd.projectmanagement.Repositories.Trello;

import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Models.Trello.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query(
            value = """
                    select l from Board b inner join List l
                    on b.id = l.board.id
                    where b.id = :id
                    """
    )
    Optional<java.util.List<List>> getBoardListsById(Integer id);
}
