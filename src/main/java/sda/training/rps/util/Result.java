package sda.training.rps.util;

import lombok.Getter;


@Getter
public enum Result {
    WIN(1, 0),
    LOSE(0, 1),
    DRAW(0, 0);

    int playerPoint;
    int computerPoint;

    Result(int playerPoint, int computerPoint) {
        this.playerPoint = playerPoint;
        this.computerPoint = computerPoint;
    }

}
