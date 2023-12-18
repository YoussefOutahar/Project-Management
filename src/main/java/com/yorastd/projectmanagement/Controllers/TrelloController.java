package com.yorastd.projectmanagement.Controllers;

import com.yorastd.projectmanagement.Models.Trello.Board;
import com.yorastd.projectmanagement.Services.TrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/{projectID}/trello")
@RequiredArgsConstructor
public class TrelloController {
    private final TrelloService trelloService;


    @GetMapping("get/boards/all")
    public ResponseEntity<List<Board>> getAllBoards(@PathVariable Integer projectID) {
        try {
            List<Board> boards = trelloService.getBoardsByProjectId(projectID);
            return ResponseEntity.ok(boards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("create/board")
    public ResponseEntity<Board> createBoard(@PathVariable Integer projectID, @RequestBody Board board) {
        try {
            trelloService.createBoard(projectID, board);
            return ResponseEntity.ok(board);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("update/board/{boardID}")
    public ResponseEntity<Board> updateBoard(@PathVariable Integer projectID, @PathVariable Integer boardID, @RequestBody Board board) {
        try {
            trelloService.updateBoard(projectID, boardID, board);
            return ResponseEntity.ok(board);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/board/{boardID}")
    public ResponseEntity deleteBoard(@PathVariable Integer projectID, @PathVariable Integer boardID) {
        try {
            trelloService.deleteBoard(projectID, boardID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("get/lists/{boardId}")
    public ResponseEntity<List<com.yorastd.projectmanagement.Models.Trello.List>> getBoardLists(@PathVariable Integer boardId) {
        try {
            List<com.yorastd.projectmanagement.Models.Trello.List> lists = trelloService.getBoardLists(boardId);
            return ResponseEntity.ok(lists);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("create/list/{boardId}")
    public ResponseEntity<com.yorastd.projectmanagement.Models.Trello.List> createBoardList(@PathVariable Integer boardId, @RequestBody com.yorastd.projectmanagement.Models.Trello.List list) {
        try {
            trelloService.createBoardList(boardId, list);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("update/list/{listId}")
    public ResponseEntity<com.yorastd.projectmanagement.Models.Trello.List> updateBoardList(@PathVariable Integer listId, @RequestBody com.yorastd.projectmanagement.Models.Trello.List list) {
        try {
            trelloService.updateBoardList(listId, list);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/list/{listId}")
    public ResponseEntity deleteBoardList(@PathVariable Integer listId) {
        try {
            trelloService.deleteBoardList(listId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("get/cards/{listId}")
    public ResponseEntity<List<com.yorastd.projectmanagement.Models.Trello.Card>> getBoardListCards(@PathVariable Integer boardId, @PathVariable Integer listId) {
        try {
            List<com.yorastd.projectmanagement.Models.Trello.Card> cards = trelloService.getBoardListCards(boardId, listId);
            return ResponseEntity.ok(cards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("create/card/{listId}")
    public ResponseEntity<com.yorastd.projectmanagement.Models.Trello.Card> createBoardListCard(@PathVariable Integer listId, @RequestBody com.yorastd.projectmanagement.Models.Trello.Card card) {
        try {
            trelloService.createBoardListCard(listId, card);
            return ResponseEntity.ok(card);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("update/card/{cardId}")
    public ResponseEntity<com.yorastd.projectmanagement.Models.Trello.Card> updateBoardListCard(@PathVariable Integer cardId, @RequestBody com.yorastd.projectmanagement.Models.Trello.Card card) {
        try {
            trelloService.updateBoardListCard(cardId, card);
            return ResponseEntity.ok(card);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/card/{cardId}")
    public ResponseEntity deleteBoardListCard(@PathVariable Integer cardId) {
        try {
            trelloService.deleteBoardListCard(cardId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
