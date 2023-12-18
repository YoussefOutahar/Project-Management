package com.yorastd.projectmanagement.Services;

import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Models.Trello.Card;
import com.yorastd.projectmanagement.Repositories.ProjectRepository;
import com.yorastd.projectmanagement.Repositories.Trello.BoardRepository;
import com.yorastd.projectmanagement.Repositories.Trello.CardRepository;
import com.yorastd.projectmanagement.Repositories.Trello.ListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrelloService {

    private final ProjectRepository projectRepository;
    private final BoardRepository boardRepository;
    private final ListRepository listRepository;
    private final CardRepository cardRepository;


    @Transactional
    public void createBoard(Integer projectId, Board board) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        board.setProject(project);
        boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(Integer projectId, Integer boardId, Board board) {
        Board board1 = boardRepository.findById(boardId).orElseThrow(
                () -> new RuntimeException("Board not found")
        );

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Project not found")
        );

        board1.setTitle(board.getTitle());
        board1.setDescription(board.getDescription());
        board1.setDone(board.isDone());
        board1.setArchived(board.isArchived());
        board1.setProject(project);
        boardRepository.save(board1);
    }

    @Transactional
    public void deleteBoard(Integer projectId, Integer boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new RuntimeException("Board not found")
        );
        boardRepository.delete(board);
    }

    @Transactional
    public List<Board> getBoardsByProjectId(Integer projectId) {
        return new ArrayList<>(projectRepository.getProjectBoardsById(projectId).orElseThrow(
                () -> new RuntimeException("Project not found")
        ));
    }

    @Transactional
    public void setBoardsToProject(Integer projectId, List<Board> boards) {
        boards.forEach(board -> {
            board.setProject(projectRepository.findById(projectId).orElseThrow(
                    () -> new RuntimeException("Project not found")
            ));
            boardRepository.save(board);
        });
    }

    @Transactional
    public List<com.yorastd.projectmanagement.Models.Trello.List> getBoardLists(Integer boardId) {
        return new ArrayList<>(boardRepository.getBoardListsById(boardId).orElseThrow(
                () -> new RuntimeException("Board not found")
        ));
    }

    @Transactional
    public void createBoardList(Integer boardId, com.yorastd.projectmanagement.Models.Trello.List list) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new RuntimeException("Board not found")
        );
        list.setBoard(board);
        listRepository.save(list);
    }

    @Transactional
    public void createBoardListCard(Integer listId, Card card) {
        com.yorastd.projectmanagement.Models.Trello.List list = listRepository.findById(listId).orElseThrow(
                () -> new RuntimeException("List not found")
        );
        Board board = boardRepository.findById(list.getBoard().getId()).orElseThrow(
                () -> new RuntimeException("Board not found")
        );
        card.setBoard(board);
        card.setList(list);
        cardRepository.save(card);
    }

    @Transactional
    public List<Card> getBoardListCards(Integer boardId, Integer listId) {
        return new ArrayList<>(listRepository.getListCardsById(listId).orElseThrow(
                () -> new RuntimeException("List not found")
        ));
    }

    @Transactional
    public void deleteBoardList(Integer listId) {
        com.yorastd.projectmanagement.Models.Trello.List list = listRepository.findById(listId).orElseThrow(
                () -> new RuntimeException("List not found")
        );
        listRepository.delete(list);
    }

    @Transactional
    public void updateBoardList(Integer listId, com.yorastd.projectmanagement.Models.Trello.List list) {
        com.yorastd.projectmanagement.Models.Trello.List list1 = listRepository.findById(listId).orElseThrow(
                () -> new RuntimeException("List not found")
        );
        list1.setTitle(list.getTitle());
        list1.setDescription(list.getDescription());
        list1.setStatus(list.getStatus());
        list1.setBoard(list.getBoard());
        listRepository.save(list1);
    }

    public void updateBoardListCard(Integer cardId, Card card) {
        Card card1 = cardRepository.findById(cardId).orElseThrow(
                () -> new RuntimeException("Card not found")
        );
        card1.setTitle(card.getTitle());
        card1.setDescription(card.getDescription());
        card1.setArchived(card.isArchived());
        card1.setDone(card.isDone());
        card1.setList(card.getList());
        card1.setBoard(card.getBoard());
        cardRepository.save(card1);
    }

    public void deleteBoardListCard(Integer cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(
                () -> new RuntimeException("Card not found")
        );
        cardRepository.delete(card);
    }
}
