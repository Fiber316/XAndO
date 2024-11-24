package com.game.xando;

import java.io.File;

class HumanPlayer extends Player {
    private IOController ioController;

    public HumanPlayer(char mark, IOController ioController) {
        super(mark);
        this.ioController = ioController;
    }

    public void makeMove(Board board) {
        int row, column;
        String move;
        boolean validMove = false;
        do {
            move = ioController.getMoveInput(getMark());
            if (move.equalsIgnoreCase("Save")) {
                File savedGameFile = new File("savedgame.txt");
                Game game = new Game();
                System.out.println("test3");
                game.saveGame(savedGameFile);
                System.out.println("test4");
                continue;
            }
            if (move.length() < 2 || !Character.isAlphabetic(move.charAt(0)) || !move.substring(1).chars().allMatch(Character::isDigit)) {
                ioController.invalidMoveMessage();
                continue;
            }

            column = move.charAt(0) - 'A';
            row = Integer.parseInt(move.substring(1)) - 1;

            validMove = board.placeMark(row, column, getMark());
            if (!validMove) {
                ioController.invalidMoveMessage();
            }
        } while (!validMove);
    }
}