package com.game.xando;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class ComputerPlayer extends Player {

    private int difficultyLevel;

    public ComputerPlayer(char mark, int difficultyLevel) {
        super(mark);
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
                Random random = new Random();
                int[] randomFreeSpace = emptySpaces.get(random.nextInt(emptySpaces.size()));
                board.placeMark(randomFreeSpace[0], randomFreeSpace[1], getMark());
                System.out.println("Znak \"" + getMark() + "\" zagrany na koordynatach " + (char) ('A' + randomFreeSpace[1]) + (randomFreeSpace[0] + 1));
            }

        }
        if (difficultyLevel == 2) {
            //to do
        }
    }
}