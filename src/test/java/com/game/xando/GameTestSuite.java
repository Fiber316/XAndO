package com.game.xando;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GameTestSuite {
    private Game game;
    private Board board;


    @BeforeEach
    void setUp() {
        game = new Game();
        board = new Board(3, 3);
        game.board = board;
    }

    @Test
    public void testORowWinCondition() {
        // When
        board.placeMark(0, 0, 'O');
        board.placeMark(0, 1, 'O');
        board.placeMark(0, 2, 'O');

        // Then
        assertTrue(board.checkWin('O'));
    }

    @Test
    public void testOColumnWinCondition() {
        // When
        board.placeMark(0, 0, 'O');
        board.placeMark(1, 0, 'O');
        board.placeMark(2, 0, 'O');

        // Then
        assertTrue(board.checkWin('O'));
    }

    @Test
    public void testODiagonalWinCondition() {
        // When
        board.placeMark(0, 0, 'O');
        board.placeMark(1, 1, 'O');
        board.placeMark(2, 2, 'O');

        // Then
        assertTrue(board.checkWin('O'));
    }

    @Test
    public void testXRowWinCondition() {
        // When
        board.placeMark(0, 0, 'X');
        board.placeMark(0, 1, 'X');
        board.placeMark(0, 2, 'X');

        // Then
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testXColumnWinCondition() {
        // When
        board.placeMark(0, 0, 'X');
        board.placeMark(1, 0, 'X');
        board.placeMark(2, 0, 'X');

        // Then
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testXDiagonalWinCondition() {
        // When
        board.placeMark(0, 0, 'X');
        board.placeMark(1, 1, 'X');
        board.placeMark(2, 2, 'X');

        // Then
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testDraw() {
        // When
        board.placeMark(0, 0, 'X');
        board.placeMark(0, 1, 'O');
        board.placeMark(0, 2, 'X');
        board.placeMark(1, 0, 'O');
        board.placeMark(1, 1, 'X');
        board.placeMark(1, 2, 'O');
        board.placeMark(2, 0, 'O');
        board.placeMark(2, 1, 'X');
        board.placeMark(2, 2, 'O');

        // Then
        assertFalse(board.checkWin('X'));
        assertFalse(board.checkWin('O'));
        assertTrue(board.isFull());
    }

    @Test
    public void testInvalidMove() {
        // When
        board.placeMark(0, 0, 'X');
        boolean result = board.placeMark(0, 0, 'O');

        // Then
        assertFalse(result);
    }
}
