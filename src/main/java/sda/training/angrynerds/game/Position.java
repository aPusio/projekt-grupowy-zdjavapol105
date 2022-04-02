package sda.training.angrynerds.game;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class Position {
    private final Integer x;
    private final Integer y;

    public static Position of(int x, int y){
        return new Position(x,y);
    }

}
