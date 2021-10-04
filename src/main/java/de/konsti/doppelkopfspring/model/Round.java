package de.konsti.doppelkopfspring.model;

public class Round {

    private final boolean solo;
    private final boolean bock;
    private final int points;
    private final int player1, player2;

    public Round(boolean solo, boolean bock, int points, int player1, int player2) {
        this.solo = solo;
        this.bock = bock;
        this.points = points;
        this.player1 = player1;
        this.player2 = player2;
    }
}
