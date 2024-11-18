package com.game.xando;

class HumanPlayer extends Player {
    private IOController ioController;

    public HumanPlayer(char mark, IOController ioController) {
        super(mark);
        this.ioController = ioController;
    }

    public void makeMove(Board board) {
        int row, column;
        String move;
        do {
            move = ioController.getMoveInput(getMark());
            column = move.charAt(0) - 'A';
            row = Integer.parseInt(move.substring(1)) - 1;

            if (!board.placeMark(row, column, getMark())) {
                ioController.invalidMoveMessage();
            }
        } while (!board.placeMark(row, column, getMark()));
    }
}