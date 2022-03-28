package sda.training.angry_nerds_game.game;


import java.util.Objects;

public class Point {

    private Integer positionX;
    private Integer positionY;
    private char symbol;

    public Point(Integer positionX, Integer positionY, char symbol) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.symbol = symbol;
    }


    public Integer getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(positionX, point.positionX) && Objects.equals(positionY, point.positionY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }
}
