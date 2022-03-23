package sda.training.learning;

public enum EngineType {
    DIESEL(1, "diesel"),
    ELECTRIC(2, "electric");

    private final String text;
    private final int index;

    EngineType(int i, String diesel) {
        this.index = i;
        this.text = diesel;
    }
}
