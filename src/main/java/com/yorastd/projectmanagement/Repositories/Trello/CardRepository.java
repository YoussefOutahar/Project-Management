package com.yorastd.projectmanagement.Repositories.Trello;

import com.yorastd.projectmanagement.Models.Trello.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
