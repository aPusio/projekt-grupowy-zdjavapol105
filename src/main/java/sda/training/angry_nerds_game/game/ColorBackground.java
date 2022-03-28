package sda.training.angry_nerds_game.game;

public enum ColorBackground {
    BLACK_BACKGROUND("\u001B[40m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    PURPLE_BACKGROUND("\u001B[45m"),
    CYAN_BACKGROUND("\u001B[46m"),
    WHITE_BACKGROUND("\u001B[47m");

    String color;

    ColorBackground(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}