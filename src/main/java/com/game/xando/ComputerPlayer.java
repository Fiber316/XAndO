package com.game.xando;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class ComputerPlayer extends Player {

    private static final Random RANDOM = new Random();

    private static final char HUMAN_X_MARK = 'X';

    private static final char CPU_O_MARK = 'O';

    private IOController ioController;

    private char difficultyLevel;

    public ComputerPlayer(char mark, IOController ioController, char difficultyLevel) {
        super(mark);
        this.ioController = ioController;
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void makeMove(Board board) {
        if (difficultyLevel == '1') {
            makeRandomMove(board);
        } else if (difficultyLevel == '2') {
            if (board.getBoard().length == 3) {
                makeDifficulty2MoveFor3x3(board);
            } else if (board.getBoard().length == 10) {
                makeDifficulty2MoveFor10x10(board);
            }
        }
    }

    private void makeRandomMove(Board board) {
        List<int[]> emptySpaces = new ArrayList<>();
        char[][] currentBoard = board.getBoard();

        for (int row = 0; row < currentBoard.length; row++) {
            for (int column = 0; column < currentBoard.length; column++) {
                if (currentBoard[row][column] == ' ') {
                    emptySpaces.add(new int[]{row, column});
                }
            }
        }

        int[] randomFreeSpace = emptySpaces.get(RANDOM.nextInt(emptySpaces.size()));
        board.placeMark(randomFreeSpace[0], randomFreeSpace[1], getMark());
        ioController.computerMoveMessage(getMark(), (char) ('A' + randomFreeSpace[1]), randomFreeSpace[0] + 1);

    }

    private void makeDifficulty2MoveFor3x3(Board board) {
        if (RANDOM.nextInt(10) == 0) {
            makeRandomMove(board);
            return;
        }

        char[][] currentBoard = board.getBoard();

        int[] winningMove = findWinningMove(board, getMark());
        if (winningMove != null) {
            board.placeMark(winningMove[0], winningMove[1], getMark());
            ioController.computerMoveMessage(getMark(), (char) ('A' + winningMove[1]), winningMove[0] + 1);
            return;
        }

        int[] opponentWinningMove = findWinningMove(board, HUMAN_X_MARK);
        if (opponentWinningMove != null) {
            board.placeMark(opponentWinningMove[0], opponentWinningMove[1], getMark());
            ioController.computerMoveMessage(getMark(), (char) ('A' + opponentWinningMove[1]), opponentWinningMove[0] + 1);
            return;
        }

        if (currentBoard[1][1] == ' ') {
            board.placeMark(1, 1, getMark());
            ioController.computerMoveMessage(getMark(), 'B', '2');
            return;
        }

        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        List<int[]> availableCorners = new ArrayList<>();
        for (int[] corner : corners) {
            if (currentBoard[corner[0]][corner[1]] == ' ') {
                availableCorners.add(corner);
            }
        }
        if (!availableCorners.isEmpty()) {
            int[] randomEmptyCorner = availableCorners.get(RANDOM.nextInt(availableCorners.size()));
            board.placeMark(randomEmptyCorner[0], randomEmptyCorner[1], getMark());
            ioController.computerMoveMessage(getMark(), (char) ('A' + randomEmptyCorner[1]), randomEmptyCorner[0] + 1);
            return;
        }

        int[][] sides = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        List<int[]> availableSides = new ArrayList<>();
        for (int[] side : sides) {
            if (currentBoard[side[0]][side[1]] == ' ') {
                availableSides.add(side);
            }
        }
        if (!availableSides.isEmpty()) {
            int[] randomEmptySide = availableSides.get(RANDOM.nextInt(availableSides.size()));
            board.placeMark(randomEmptySide[0], randomEmptySide[1], getMark());
            ioController.computerMoveMessage(getMark(), (char) ('A' + randomEmptySide[1]), randomEmptySide[0] + 1);
        }
    }

    private void makeDifficulty2MoveFor10x10(Board board) {
        char[][] currentBoard = board.getBoard();

        int[] winningMove = findWinningMove(board, getMark());
        if (winningMove != null) {
            board.placeMark(winningMove[0], winningMove[1], getMark());
            ioController.computerMoveMessage(getMark(), (char) ('A' + winningMove[1]), winningMove[0] + 1);
            return;
        }

        int[] opponentWinningMove = findWinningMove(board, HUMAN_X_MARK);
        if (opponentWinningMove != null) {
            board.placeMark(opponentWinningMove[0], opponentWinningMove[1], getMark());
            ioController.computerMoveMessage(getMark(), (char) ('A' + opponentWinningMove[1]), opponentWinningMove[0] + 1);
            return;
        }

        List<int[]> adjacentEmptySpaces = getAdjacentEmptySpaces(currentBoard);


        int[] opponentWinningIn2Moves = findTwoMovesAwayWin(board, HUMAN_X_MARK);
        if (opponentWinningIn2Moves != null) {
            if (RANDOM.nextInt(3) == 0) {
                makeRandomMoveAdjacentToEmptySpace(board, adjacentEmptySpaces);
                return;
            }
            board.placeMark(opponentWinningIn2Moves[0], opponentWinningIn2Moves[1], getMark());
            ioController.computerMoveMessage(getMark(), (char) ('A' + opponentWinningIn2Moves[1]), opponentWinningIn2Moves[0] + 1);
            return;
        }


        for (int row = 0; row < currentBoard.length; row++) {
            for (int column = 0; column < currentBoard.length; column++) {
                if (currentBoard[row][column] == ' ') {
                    if (hasAdjacentMark(row, column, currentBoard)) {
                        adjacentEmptySpaces.add(new int[]{row, column});
                    }
                }
            }
        }

        makeRandomMoveAdjacentToEmptySpace(board, adjacentEmptySpaces);
    }

    private List<int[]> getAdjacentEmptySpaces(char[][] currentBoard) {
        List<int[]> adjacentEmptySpaces = new ArrayList<>();
        for (int row = 0; row < currentBoard.length; row++) {
            for (int column = 0; column < currentBoard.length; column++) {
                if (currentBoard[row][column] == ' ') {
                    if (hasAdjacentMark(row, column, currentBoard)) {
                        adjacentEmptySpaces.add(new int[]{row, column});
                    }
                }
            }
        }
        return adjacentEmptySpaces;
    }


    private void makeRandomMoveAdjacentToEmptySpace(Board board, List<int[]> adjacentEmptySpaces) {
        int[] randomAdjacentEmptySpace = adjacentEmptySpaces.get(RANDOM.nextInt(adjacentEmptySpaces.size()));
        board.placeMark(randomAdjacentEmptySpace[0], randomAdjacentEmptySpace[1], getMark());
        ioController.computerMoveMessage(getMark(), (char) ('A' + randomAdjacentEmptySpace[1]), randomAdjacentEmptySpace[0] + 1);
    }

    private boolean hasAdjacentMark(int row, int column, char[][] board) {
        int[] rowOffsets = {-1, 0, 1};
        int[] columnOffsets = {-1, 0, 1};

        for (int rowOffset : rowOffsets) {
            for (int columnOffset : columnOffsets) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }
                int checkedRow = row + rowOffset;
                int checkedColumn = column + columnOffset;
                if (checkedRow >= 0 && checkedRow < board.length && checkedColumn >= 0 && checkedColumn < board.length) {
                    if (board[checkedRow][checkedColumn] != ' ') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int[] findWinningMove(Board board, char mark) {
        char[][] currentBoard = board.getBoard();
        for (int row = 0; row < currentBoard.length; row++) {
            for (int column = 0; column < currentBoard.length; column++) {
                if (currentBoard[row][column] == ' ') {

                    currentBoard [row][column] = mark;

                    if (board.checkWin(mark)) {
                        currentBoard[row][column] = ' ';
                        return new int[]{row, column};
                    }

                    currentBoard[row][column] = ' ';
                }
            }
        }
        return null;
    }
    private int[] findTwoMovesAwayWin(Board board, char mark) {
        char[][] currentBoard = board.getBoard();
        for (int row = 0; row < currentBoard.length; row++) {
            for (int column = 0; column < currentBoard.length; column++) {
                if (currentBoard[row][column] == ' ') {
                    currentBoard[row][column] = mark;
                    int[] nextMove = findWinningMove(board, mark);
                    currentBoard[row][column] = ' ';

                    if (nextMove != null) {
                        currentBoard[row][column] = CPU_O_MARK;
                        boolean checkTwoMoves = checkTwoMovesToWin(board, mark);

                        if (!checkTwoMoves) {
                            currentBoard[row][column] = ' ';
                            return new int[]{row, column};
                        }
                        currentBoard[row][column] = ' ';

                    }
                }
            }
        }
        return null;
    }

    private boolean checkTwoMovesToWin(Board board, char mark) {
        char[][] currentBoard = board.getBoard();
        for (int row = 0; row < currentBoard.length; row++) {
            for (int column = 0; column < currentBoard.length; column++) {
                if (currentBoard[row][column] == ' ') {
                    currentBoard[row][column] = mark;
                    int[] blockAttemptMove = findWinningMove(board, mark);

                    if (blockAttemptMove != null) {
                        currentBoard[blockAttemptMove[0]][blockAttemptMove[1]] = CPU_O_MARK;
                        int[] secondMove = findWinningMove(board, mark);
                        currentBoard[row][column] = ' ';
                        currentBoard[blockAttemptMove[0]][blockAttemptMove[1]] = ' ';
                        if (secondMove != null) {
                            currentBoard[row][column] = ' ';
                            return true;
                        }
                    }
                    currentBoard[row][column] = ' ';
                }
            }
        }
        return false;
    }

}