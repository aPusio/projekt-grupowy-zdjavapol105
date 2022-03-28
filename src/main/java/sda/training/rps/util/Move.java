package sda.training.rps.util;

import java.util.Arrays;

public enum Move {
    ROCK(0, "Kamień"),
    WATER(1, "Woda"),
    AIR(2, "Powietrze"),
    PAPER(3, "Papier"),
    SPONGE(4, "Gabka"),
    SCISSORS(5, "Nożyczki"),
    FIRE(6, "Ogień"),
    OTHER(7, "Błędny wybór");

    private final int moveInt;
    private final String moveString;

    Move(int moveInt, String moveString) {
        this.moveInt = moveInt;
        this.moveString = moveString;
    }

    public int getMoveInt() {
        return moveInt;
    }

    public String getMoveString() {
        return moveString;
    }

    public static Move getMoveById(int moveByInt){
        return Arrays.stream(Move.values())
                .filter(a -> a.getMoveInt() == moveByInt)
                .findFirst()
                .orElse(OTHER);
    }
}
