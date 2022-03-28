package sda.training.angry_nerds_game.game;

import java.util.HashSet;
import java.util.Set;

public class Shot {

    private Set<Point> shot = new HashSet<>();

    public Set<Point> getShot() {
        return shot;
    }

    //TODO oszacować ile elementów strzału generować narazie jest 30

    public void genarateShotLine(Integer velocityX, Integer velocityY, Integer wind, Integer gravity) {

        int posX = 0;
        int posY = 0;

        for (int i = 0; i < 30; i++) {
            posX = posX + velocityX;
            posY = posY + velocityY;
            shot.add(new Point(posX, posY, '*'));
            velocityX = velocityX + wind;
            velocityY = velocityY - gravity;
            if (velocityX < 0) {
                velocityX = 0;
            }
        }
    }

    //TODO zrobić ciągłą shotline

    //TODO Karol zaproponował rodzaj broni (zróbmy na początek wieksze pociski)

}
