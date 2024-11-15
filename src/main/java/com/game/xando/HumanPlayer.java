package com.game.xando;

class HumanPlayer extends Player {
    private IOController ioController;

    public HumanPlayer(char mark, IOController ioController) {
        super(mark);
        this.ioController = ioController;
    }

    public void makeMove(Board board) {
        while (true) {
            String move = ioController.getMoveInput(getMark());
            int column = move.charAt(0) - 'A';
            int row = Integer.parseInt(move.substring(1)) - 1;

            if (board.placeMark(row, column, getMark())) {
                break;
            } else {
                System.out.println("Nieprawidlowy ruch");
            }
        }
    }
}