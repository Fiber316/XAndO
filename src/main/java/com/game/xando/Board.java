package com.game.xando;

import java.util.Arrays;

public class Board {
    private char[][] board;
    private int boardSize;
    private IOController ioController = new IOController();

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }

    public boolean isFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean placeMark(int row, int column, char mark) {
        if (row >= 0 && row < boardSize && column >= 0 && column < boardSize && board[row][column] == ' ') {
            board[row][column] = mark;
            return true;
        } else {
            return false;
        }
    }

    public void printBoard() {
        ioController.printBoard(board, boardSize);
    }

    public char[][] getBoard() {
        return board;
    }
}
