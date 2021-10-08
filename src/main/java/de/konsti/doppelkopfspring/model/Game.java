package de.konsti.doppelkopfspring.model;

import java.util.*;
import java.util.stream.IntStream;

public class Game {

    private final boolean fivePlayer;
    private final Player[] players;
    private int dealer;
    private final List<Round> rounds;

    public Game(boolean fivePlayer, String... names) {
        this.fivePlayer = fivePlayer;
        this.players = Arrays.stream(names).limit(fivePlayer ? 5 : 4).map(Player::new).toArray(Player[]::new);
        dealer = 0;
        this.rounds = new LinkedList<>();
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getDealer() {
        return dealer;
    }

    public void addRound(boolean solo, boolean soloWon, boolean bock, int points, int player1, int player2) {
        List<Round> lastFour = rounds.subList(Math.max(rounds.size() - 4, 0), rounds.size());

        int bucks = lastFour.stream().mapToInt(round -> round.bock() ? 1 : 0).sum();

        int[] winners;

        if (solo) {
            if (soloWon) {
                winners = new int[]{player1};
            } else {
                winners = IntStream.range(0, players.length).filter(player -> player != player1).toArray();
            }
        } else {
            winners = new int[]{player1, player2};
        }

        Round round = new Round(bock, points, bucks, winners);
        rounds.add(round);

        if (fivePlayer || !solo) {
            dealer = ++dealer % winners.length;
        }

        for (int winner : winners) {
            players[winner].addPoints(round.getMultipliedPoints());
        }
    }

    public void deleteRound() {
        Round round = rounds.remove(rounds.size() - 1);

        if (round != null) {
            for (int winner : round.winners()) {
                players[winner].addPoints(-round.getMultipliedPoints());
            }
        }
    }

}
