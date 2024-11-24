package com.game.xando;

import java.util.Scanner;

public class IOController {
    private Scanner scanner = new Scanner(System.in);

    public boolean askToLoadGame() {
        System.out.println("Czy chcesz wczytac poprzednio zapisana gre? (tak/nie)");
        String response = scanner.next().toLowerCase();
        while (!response.equals("tak") && !response.equals("nie")) {
            System.out.println("Zly input - wybierz ponownie (tak/nie)");
            response = scanner.next().toLowerCase();
        }
        return response.equals("tak");
    }

    public void selectGameMode(Game game) {
        System.out.println("""
                Wybierz tryb gry:
                1 - uloz 3 pod rzad na polu 3/3
                2 - uloz 5 pod rzad na polu 10/10""");
        char mode;
        do {
            mode = scanner.next().charAt(0);
            if (mode == '1') {
                game.setBoardSize(3);
                game.setWinCondition(3);
            } else if (mode == '2') {
                game.setBoardSize(10);
                game.setWinCondition(5);
            } else {
                System.out.println("Zly input - wybierz ponownie");
            }
        } while (mode != '1' && mode != '2');
    }

    public char selectplayerOrCPU() {
        char playerOrCPU;
        System.out.println("""
                Gra na dwoch graczy czy przeciwko komputerowi?
                1 - gra na dwoch graczy
                2 - przeciwko komputerowi""");
        do {
            playerOrCPU = scanner.next().charAt(0);
            if (playerOrCPU != '1' && playerOrCPU != '2') {
                System.out.println("Zly input - wybierz ponownie");
            }
        } while (playerOrCPU != '1' && playerOrCPU != '2');
        return playerOrCPU;
    }

    public char selectDifficulty() {
        char difficulty;
        System.out.println("""
                Wybierz poziom trudnosci:
                1 - latwy
                2 - sredni""");
        do {
            difficulty = scanner.next().charAt(0);
            if (difficulty != '1' && difficulty != '2') {
                System.out.println("Zly input - wybierz ponownie");
            }
        } while (difficulty != '1' && difficulty != '2');
        return difficulty;
    }

    public String getMoveInput(char mark) {
        System.out.println("Ruch gracza \"" + mark + "\". Wpisz swoj ruch (np: B2):");
        return scanner.next();
    }

    public void computerMoveMessage(char mark, char column, int row) {
        System.out.println("Znak \"" + mark + "\" zagrany na koordynatach " + column + row);

    }

    public void winnerMessage(char mark) {
        System.out.println(mark + " wygral");
    }

    public void drawMessage() {
        System.out.println("Remis");
    }

    public void printBoard(char[][] board, int boardSize) {
        System.out.print("   ");

        for (int column = 0; column < boardSize; column++) {
            System.out.print(" " + (char) ('A' + column));
        }
        System.out.println();

        for (int row = 0; row < boardSize; row++) {
            if (row < 9) {
                System.out.print((row + 1) + "  |");
            } else {
                System.out.print((row + 1) + " |");
            }
            for (int column = 0; column < boardSize; column++) {
                System.out.print(board[row][column] + "|");
            }
            System.out.println();
        }
    }

    public void invalidMoveMessage() {
        System.out.println("Nieprawidlowy ruch");
    }
    public void saveGameSuccessMessage() {
        System.out.println("Gra zapisana");
    }

    public void saveGameErrorMessage() {
        System.out.println("Blad podczas zapisywania gry");
    }

    public void loadGameSuccessMessage() {
        System.out.println("Gra zaladowana");
    }

    public void loadGameErrorMessage() {
        System.out.println("Blad podczas zaladowania gry");
    }
}