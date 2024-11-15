package com.game.xando;

import java.util.Scanner;

public class IOController {
    private Scanner scanner = new Scanner(System.in);

    public void selectGameMode(Game game) {
        System.out.println("""
                Wybierz tryb gry:
                1 - uloz 3 pod rzad na polu 3/3
                2 - uloz 5 pod rzad na polu 10/10""");
        char mode;
        while (true) {
            mode = scanner.next().charAt(0);
            if (mode == '1') {
                game.setBoardSize(3);
                game.setWinCondition(3);
                break;
            }
            if (mode == '2') {
                game.setBoardSize(10);
                game.setWinCondition(5);
                break;
            }
            System.out.println("Zly input - wybierz ponownie");

        }
    }

    public char selectplayerOrCPU() {
        char playerOrCPU;
        System.out.println("""
                Gra na dwoch graczy czy przeciwko komputerowi?
                1 - na dwoch graczy
                2 - przeciwko CPU""");
        while (true) {
            playerOrCPU = scanner.next().charAt(0);
            if (playerOrCPU == '1' || playerOrCPU == '2') {
                return playerOrCPU;
            } else {
                System.out.println("Zly input - wybierz ponownie");
            }
        }
    }

    public String getMoveInput(char mark) {
        System.out.println("Ruch gracza \"" + mark + "\". Wpisz swoj ruch (np: B2):");
        return scanner.next();
    }

    public void winnerMessage(char mark) {
        System.out.println(mark + " wygral");
    }

    public void drawMessage() {
        System.out.println("Remis");
    }
}