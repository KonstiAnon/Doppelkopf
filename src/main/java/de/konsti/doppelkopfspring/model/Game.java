package de.konsti.doppelkopfspring.model;

import java.util.Arrays;

public class Game {

    private final boolean fivePlayer;
    private final Player[] players;

    public Game(boolean fivePlayer, String... names) {
        this.players = Arrays.stream(names).limit(fivePlayer ? 5 : 4).map(Player::new).toArray(Player[]::new);
        this.fivePlayer = fivePlayer;
    }

    public Player[] getPlayers() {
        return players;
    }
}
