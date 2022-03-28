package sda.training.angry_nerds_game.game;

import java.util.HashSet;
import java.util.Set;

public class Target {

    private Set<Point> target = new HashSet<>();


    public Set<Point> getTarget() {
        return target;
    }

    public void initBoxTarget(int x, int y, int dX, int dY, char symbol){

        for (int i = y; i < y+dY; i++) {
            for (int j = x; j < x+dX; j++) {
                this.target.add(new Point(j,i,symbol));
            }
        }
    }

        //TODO : Zrobić inne kształty Targeta np koło trójkąt ew zrobić edytor kształtu do trafienia

}
