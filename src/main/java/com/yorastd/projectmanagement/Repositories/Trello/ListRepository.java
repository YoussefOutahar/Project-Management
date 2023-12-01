package com.yorastd.projectmanagement.Repositories.Trello;

import com.yorastd.projectmanagement.Models.Trello.Card;
import com.yorastd.projectmanagement.Models.Trello.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {

    @Query(
            value = """
                    select c from List l inner join Card c
                    on l.id = c.list.id
                    where l.id = :id
                    """
    )
    Optional<java.util.List<Card>> getListCardsById(Long id);
}
