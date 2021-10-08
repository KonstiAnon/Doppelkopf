package de.konsti.doppelkopfspring.model;

public record Round(boolean bock, int points, int bucks, int[] winners) {
    public int getMultipliedPoints() {
        int multipliedPoints = points * (int) Math.pow(2, bucks);
        if (winners.length == 1) {
            return 3 * multipliedPoints;
        }
        return multipliedPoints;
    }
}
