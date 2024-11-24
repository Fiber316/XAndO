package com.game.xando;

import java.io.*;

public class Game {
    private static final char PLAYER_1_MARK = 'X';
    private static final char PLAYER_2_MARK = 'O';


    Board board;
    Player player1;
    Player player2;
    char playerOrCPU;
    private int boardSize;
    IOController ioController;
    private int winCondition;
    private char difficultyLevel;
    private Player currentPlayer;
    public File savedGameFile = new File("savedgame.txt");

    public void start() {
        ioController = new IOController();

        if (ioController.askToLoadGame()) {
            loadGame(savedGameFile);
            playGame();
        }

        ioController.selectGameMode(this);

        board = new Board(boardSize, winCondition);

        playerOrCPU = ioController.selectplayerOrCPU();

        if (playerOrCPU == '2') {
            difficultyLevel = ioController.selectDifficulty();
        }

        player1 = new HumanPlayer(PLAYER_1_MARK, ioController);
        if (playerOrCPU == '1') {
            player2 = new HumanPlayer(PLAYER_2_MARK, ioController);
        } else if (playerOrCPU == '2') {
            player2 = new ComputerPlayer(PLAYER_2_MARK, ioController, difficultyLevel);
        }

        playGame();
    }

    private void playGame() {
        Player currentPlayer = player1;
        while (true) {
            board.printBoard();
            currentPlayer.makeMove(board);
            if (board.checkWin(currentPlayer.getMark())) {
                board.printBoard();
                ioController.winnerMessage(currentPlayer.getMark());
                break;
            } else if (board.isFull()) {
                board.printBoard();
                ioController.drawMessage();
                break;
            }
            currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
        }
    }

    public void saveGame(File savedGameFile) {
        try {
            FileOutputStream fileStream = new FileOutputStream(savedGameFile);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(boardSize);
            objectStream.writeObject(winCondition);
            objectStream.writeObject(difficultyLevel);
            objectStream.writeObject(board);
            objectStream.writeObject(currentPlayer);
            objectStream.writeObject(playerOrCPU);

            objectStream.close();
            fileStream.close();
            ioController.saveGameSuccessMessage();
        } catch (Exception e) {
            ioController.saveGameErrorMessage();
        }
    }
    public void loadGame(File savedGameFile)  {
        try {
            FileInputStream fileStream = new FileInputStream(savedGameFile);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            boardSize = (Integer) objectStream.readObject();
            winCondition = (Integer) objectStream.readObject();
            difficultyLevel = (Character) objectStream.readObject();
            board = (Board) objectStream.readObject();
            currentPlayer = (Player) objectStream.readObject();
            playerOrCPU = (Character) objectStream.readObject();

            objectStream.close();
            fileStream.close();
            ioController.loadGameSuccessMessage();
        } catch (Exception e) {
            ioController.loadGameErrorMessage();
        }
    }

    public void setBoardSize(int size) {
        this.boardSize = size;
    }

    public void setWinCondition(int winCondition) {
        this.winCondition = winCondition;
    }

}