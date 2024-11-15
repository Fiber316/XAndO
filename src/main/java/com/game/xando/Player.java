package com.game.xando;

abstract class Player {
    private char mark;

    public Player(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }

    public abstract void makeMove(Board board);
}
