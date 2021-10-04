package de.konsti.doppelkopfspring.model;

public class Player {
    private final String name;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }

    public String getName() {
        return this.name;
    }
}
