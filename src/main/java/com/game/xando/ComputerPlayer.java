package com.game.xando;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class ComputerPlayer extends Player {

    private static final Random RANDOM = new Random();

    private IOController ioController;

    private int difficultyLevel;

    public ComputerPlayer(char mark, IOController ioController, int difficultyLevel) {
        super(mark);
        this.ioController = ioController;
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void makeMove(Board board) {
        if (difficultyLevel == 1) {
            List<int[]> emptySpaces = new ArrayList<>();
            char[][] currentBoard = board.getBoard();

            for (int row = 0; row < currentBoard.length; row++) {
                for (int column = 0; column < currentBoard[row].length; column++) {
                    if (currentBoard[row][column] == ' ') {
                        emptySpaces.add(new int[]{row, column});
                    }
                }
            }

            if (!emptySpaces.isEmpty()) {
                int[] randomFreeSpace = emptySpaces.get(RANDOM.nextInt(emptySpaces.size()));
                board.placeMark(randomFreeSpace[0], randomFreeSpace[1], getMark());
                ioController.computerMoveMessage(getMark(),(char) ('A' + randomFreeSpace[1]), randomFreeSpace[0] + 1);
            }

        }
        if (difficultyLevel == 2) {
            //to do
        }
    }
}