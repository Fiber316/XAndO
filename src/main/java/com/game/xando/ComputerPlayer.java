package com.game.xando;

import java.util.Random;

class ComputerPlayer extends Player {
    public ComputerPlayer(char mark) {
        super(mark);
    }

    @Override
    public void makeMove(Board board) {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(board.getBoard().length);
            int column = random.nextInt(board.getBoard().length);

            if (board.placeMark(row, column, getMark())) {
                System.out.println("Znak \"" + getMark() + "\" zagrany na koordynatach " + (char) ('A' + column) + (row + 1));
                break;
            }
        }
    }
}