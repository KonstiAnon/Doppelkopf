package de.konsti.doppelkopfspring.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Game {

    private final boolean fivePlayer;
    private final Player[] players;
    private final LinkedList<Round> rounds;

    public Game(boolean fivePlayer, String... names) {
        this.players = Arrays.stream(names).limit(fivePlayer ? 5 : 4).map(Player::new).toArray(Player[]::new);
        this.fivePlayer = fivePlayer;
        this.rounds = new LinkedList<>();
    }

    public Player[] getPlayers() {
        return players;
    }
}
