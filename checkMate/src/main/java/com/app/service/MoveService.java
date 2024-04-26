package com.app.service;

import com.app.bean.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.Math.abs;

@Service
@Slf4j
public class MoveService {

    public Game makeMove(MoveDto moveDto) {
        Game game = new Game();
        validateMove(game, moveDto);
        Cell cell = game.getBoard()[moveDto.getPrevX()][moveDto.getPrevY()];
        Piece currentPiece = cell.getPiece();
        Cell targetCell = game.getBoard()[moveDto.getNewX()][moveDto.getNewY()];
        targetCell.setPiece(currentPiece);
        return game;
    }

    private void validateMove(Game game, MoveDto moveDto) {
        validateMoveByRole(moveDto, game);
//        validateCheckMate();
    }

    private void validateMoveByRole(MoveDto moveDto, Game game) {
        Cell currentCell = game.getBoard()[moveDto.getPrevX()][moveDto.getPrevY()];
        Cell targetCell = game.getBoard()[moveDto.getNewX()][moveDto.getNewY()];
        Piece currentPiece = currentCell.getPiece();
        int prevX = moveDto.getPrevX();
        int prevY = moveDto.getPrevY();
        int newX = moveDto.getNewX();
        int newY = moveDto.getNewY();
        boolean notDiagonal = abs(prevY - newY) != abs(prevX - newX);
        boolean isBlackPiece = currentPiece.getColor()==Color.BLACK;
        switch (currentPiece.getRole()) {
            case KING :
                if (abs(prevY-newY)+abs(prevX-newX)!=1) {
                    invalidPieceMove();
                }
                break;
            case QUEEN:
                if (newX!=prevX && newY!=prevY || notDiagonal) {
                    invalidPieceMove();
                }
                break;
            case BISHOP:
                if (notDiagonal) {
                    invalidPieceMove();
                }
                break;
            case ROOK:
                if (newX!=prevX && newY!=prevY) {
                    invalidPieceMove();
                }
                break;
            case KNIGHT:
                if (!(abs(newY-prevY)==2 && abs(newX-prevX)==1) || !(abs(newY-prevY)==1 && abs(newX-prevX)==2)) {
                    invalidPieceMove();
                }
                break;
            case PAWN:
                if (invalidPawnMove(targetCell, newY, prevY, isBlackPiece, newX, prevX)) {
                    invalidPieceMove();
                }
                break;

        }
    }

    private static boolean invalidPawnMove(Cell targetCell, int newY, int prevY, boolean isBlackPiece, int newX, int prevX) {
        return (targetCell.getPiece() != null && (newY - prevY != (isBlackPiece ? 1 : -1) || (newY - prevY == (isBlackPiece ? 1 : -1) && abs(newX - prevX) != 1)))
                || (newY - prevY != (isBlackPiece ? 1 : -1)) && (abs(newY - prevY) == 2 && prevY != (isBlackPiece ? 1 : 6));
    }

    private void invalidPieceMove() {
        throw new RuntimeException("Invalid move");
    }
}
