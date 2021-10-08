package de.konsti.doppelkopfspring.model;

import java.util.*;

public class Game {

    private final boolean fivePlayer;
    private final Player[] players;
    private final List<Round> rounds;

    public Game(boolean fivePlayer, String... names) {
        this.players = Arrays.stream(names).limit(fivePlayer ? 5 : 4).map(Player::new).toArray(Player[]::new);
        this.fivePlayer = fivePlayer;
        this.rounds = new LinkedList<>();
    }

    public Player[] getPlayers() {
        return players;
    }
    public void addRound(boolean solo, boolean bock, int points, int player1, int player2){
        rounds.add(new Round(solo, bock, points, player1, player2));
    }
    public Entry[] getEntries(){
        Entry[] entries = new Entry[rounds.size()];
        Iterator<Round> iter = rounds.listIterator();
        int[] multipliers = new int[4];
        for(int i = 0; i < rounds.size(); i++){
            Round round = iter.next();
            int bucks = get(multipliers);
            int mul = 2 ^ bucks;
            if(round.bock())
                add(multipliers);
            entries[i] = new Entry(round.solo(), bucks, mul * round.points(), round.player1(), round.player2());
        }
        return entries;
    }

    private void rotate(int[] multipliers){
        for(int i = 0; i< multipliers.length - 1; i++){
            multipliers[i] = multipliers[i+1];
        }
        multipliers[3] = 0;
    }

    private void add(int[] multipliers){
        for(int i = 0; i < multipliers.length; i++){
            multipliers[i]++;
        }
    }
    private int get(int[] multipliers){
        int toRet = multipliers[0];
        rotate(multipliers);
        return toRet;
    }
}
