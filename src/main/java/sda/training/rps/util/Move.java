package sda.training.rps.util;

public enum Move {
    ROCK(0, "Kamień"),
    WATER(1, "Woda"),
    AIR(2, "Powietrze"),
    PAPER(3, "Papier"),
    SPONGE(4, "Gabka"),
    SCISSORS(5, "Nożyczki"),
    FIRE(6, "Ogień");

    private int moveInt;
    private String moveString;

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
}
