package com.game.xando;

public class Game {
    Board board;
    private Player player1;
    private Player player2;
    private int boardSize;
    private IOController ioController;
    private int winCondition;

    public void setBoardSize(int size) {
        this.boardSize = size;
    }

    public void setWinCondition(int winCondition) {
        this.winCondition = winCondition;
    }

    public Game() {
        ioController = new IOController();
    }

    public void start() {
        ioController.selectGameMode(this);

        board = new Board(boardSize);

        char playerOrCPU = ioController.selectplayerOrCPU();

        player1 = new HumanPlayer('X', ioController);
        if (playerOrCPU == '1') {
            player2 = new HumanPlayer('O', ioController);
        } else if (playerOrCPU == '2') {
            player2 = new ComputerPlayer('O');
        }

        playGame();
    }

    private void playGame() {
        Player currentPlayer = player1;
        while (true) {
            board.printBoard();
            currentPlayer.makeMove(board);
            if (checkWin(currentPlayer.getMark())) {
                board.printBoard();
                ioController.winnerMessage(currentPlayer.getMark());
                break;
            } else if (board.isFull()) {
                board.printBoard();
                ioController.drawMessage();
                break;
            }
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }

    boolean checkWin(char mark) {
        return checkRows(mark) || checkColumns(mark) || checkDiagonalsToBottomRight(mark) || checkDiagonalsToBottomLeft(mark);
    }

    private boolean checkRows(char mark) {
        char[][] boardCheck = board.getBoard();
        for (int row = 0; row < boardCheck.length; row++) {
            int count = 0;
            for (int column = 0; column < boardCheck[row].length; column++) {
                if (boardCheck[row][column] == mark) {
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
        char[][] boardCheck = board.getBoard();
        for (int column = 0; column < boardCheck[0].length; column++) {
            int count = 0;
            for (int row = 0; row < boardCheck.length; row++) {
                if (boardCheck[row][column] == mark) {
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
        char[][] boardCheck = board.getBoard();

        for (int startRow = 0; startRow < boardCheck.length; startRow++) {
            for (int startColumn = 0; startColumn < boardCheck.length; startColumn++) {
                int count = 0;
                for (int step = 0; step < winCondition; step++) {
                    int currentRow = startRow + step;
                    int currentColumn = startColumn + step;

                    if (currentRow < boardCheck.length && currentColumn < boardCheck.length && boardCheck[currentRow][currentColumn] == mark) {
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
        char[][] boardCheck = board.getBoard();

        for (int startRow = 0; startRow < boardCheck.length; startRow++) {
            for (int startColumn = 0; startColumn < boardCheck.length; startColumn++) {
                int count = 0;
                for (int step = 0; step < winCondition; step++) {
                    int currentRow = startRow + step;
                    int currentColumn = startColumn - step;

                    if (currentRow < boardCheck.length && currentColumn >= 0 && boardCheck[currentRow][currentColumn] == mark) {
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

}