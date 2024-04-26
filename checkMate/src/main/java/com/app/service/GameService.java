package com.app.service;

import com.app.bean.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameService {

    public Game startGame(Players players) {
        Game game = initGame(players);
        return game;
    }

    public Game resumeGame(String gameId) {
        Game game = new Game();
        return game;
    }

    private Game initGame(Players players) {
        Game game = new Game();
        game.setId("1234");
        game.setPlayers(players);
        game.setStatus(GameStatus.STARTED);
        game.setBoard(new Cell[8][8]);
        populateDefaultBoard(game.getBoard());
        return game;
    }

    private void populateDefaultBoard(Cell[][] cells) {
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                cells[i][j] = new Cell();
                if ((i+j)%2==0) {
                    cells[i][j].setCellColor(Color.BLACK);
                } else {
                    cells[i][j].setCellColor(Color.WHITE);
                }
            }
        }
        populateMainPieces(cells, 0, Color.BLACK);
        populatePawns(cells[1], Color.BLACK);
        populatePawns(cells[6], Color.WHITE);
        populateMainPieces(cells, 7, Color.WHITE);
    }

    private void populateMainPieces(Cell[][] cells, int row, Color color) {
        for (int i=0;i<8;i++) {
            Piece piece = new Piece();
            piece.setColor(color);
            piece.setDead(false);
            if (i==0 || i==7) {
                piece.setRole(Role.ROOK);
            } else if (i==1 || i==6) {
                piece.setRole(Role.KNIGHT);
            } else if (i==2 || i==5) {
                piece.setRole(Role.BISHOP);
            } else if (row%2!=0) {
                piece.setRole(Role.QUEEN);
            } else {
                piece.setRole(Role.KING);
            }
            cells[row][i].setPiece(piece);
        }
    }

    private void populatePawns(Cell[] row, Color color) {
        for (Cell cell : row) {
            Piece piece = new Piece();
            piece.setColor(color);
            piece.setDead(false);
            piece.setRole(Role.PAWN);
            cell.setPiece(piece);
        }
    }

}
