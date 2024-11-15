package com.game.xando;

import java.util.Arrays;

public class Board {
    private char[][] board;
    private int boardSize;

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
        System.out.print("   ");

        for (int column = 0; column < boardSize; column++) {
            System.out.print(" " + (char) ('A' + column));
        }
        System.out.println();

        for (int row = 0; row < boardSize; row++) {
            if (row < 9) {
                System.out.print((row + 1) + "  |");
            } else {
                System.out.print((row + 1) + " |");
            }
            for (int column = 0; column < boardSize; column++) {
                System.out.print(board[row][column] + "|");
            }
            System.out.println();
        }
    }

    public char[][] getBoard() {
        return board;
    }
}
