package com.yorastd.projectmanagement.Services;

import com.yorastd.projectmanagement.Models.Trello.Board;
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

    private ProjectRepository projectRepository;
    private final BoardRepository boardRepository;
    private final ListRepository listRepository;
    private final CardRepository cardRepository;

    @Transactional
    public Board createBoard() {
        Board board = new Board();
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public List<Board> getAllBoards() {
        return new ArrayList<>(boardRepository.findAll());
    }

    @Transactional
    public List<Board> getBoardsByProjectId(long projectId) {
        return new ArrayList<>(projectRepository.getProjectBoardsById(projectId).orElseThrow(
                () -> new RuntimeException("Project not found")
        ));
    }

    @Transactional
    public List<Board> setBoardsToProject(long projectId, List<Board> boards) {
        boards.forEach(board -> {
            board.setProject(projectRepository.findById(projectId).orElseThrow(
                    () -> new RuntimeException("Project not found")
            ));
            boardRepository.save(board);
        });
        return boards;
    }
}
