package com.game.xando;

import java.util.Arrays;

public class Board {
    private int winCondition;
    private char[][] board;
    private int boardSize;
    private IOController ioController = new IOController();

    public Board(int boardSize, int winCondition) {
        this.boardSize = boardSize;
        this.winCondition = winCondition;
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

    public boolean checkWin(char mark) {
        return checkRows(mark) || checkColumns(mark) || checkDiagonalsToBottomRight(mark) || checkDiagonalsToBottomLeft(mark);
    }

    private boolean checkRows(char mark) {
        for (int row = 0; row < board.length; row++) {
            int count = 0;
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column] == mark) {
                    count++;
                    if (count == winCondition) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkColumns(char mark) {
        for (int column = 0; column < board[0].length; column++) {
            int count = 0;
            for (int row = 0; row < board.length; row++) {
                if (board[row][column] == mark) {
                    count++;
                    if (count == winCondition) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalsToBottomRight(char mark) {
        for (int startRow = 0; startRow < board.length; startRow++) {
            for (int startColumn = 0; startColumn < board.length; startColumn++) {
                int count = 0;
                for (int step = 0; step < winCondition; step++) {
                    int currentRow = startRow + step;
                    int currentColumn = startColumn + step;

                    if (currentRow < board.length && currentColumn < board.length && board[currentRow][currentColumn] == mark) {
                        count++;
                        if (count == winCondition) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalsToBottomLeft(char mark) {
        for (int startRow = 0; startRow < board.length; startRow++) {
            for (int startColumn = 0; startColumn < board.length; startColumn++) {
                int count = 0;
                for (int step = 0; step < winCondition; step++) {
                    int currentRow = startRow + step;
                    int currentColumn = startColumn - step;

                    if (currentRow < board.length && currentColumn >= 0 && board[currentRow][currentColumn] == mark) {
                        count++;
                        if (count == winCondition) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public void printBoard() {
        ioController.printBoard(board, boardSize);
    }

    public char[][] getBoard() {
        return board;
    }
}
