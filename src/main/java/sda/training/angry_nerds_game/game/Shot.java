package sda.training.angry_nerds_game.game;

import java.util.HashSet;
import java.util.Set;

public class Shot {

    private Set<Point> shot = new HashSet<>();

    public Set<Point> getShot() {
        return shot;
    }

    public void genarateShotLine(Integer velocityX, Integer velocityY, Integer wind, Integer gravity) {

        int maxX = SingletonGameConfig.getInstance().getMaxX();
        int posX = 0;
        int posY = 0;

        while(posX < maxX || posY > 0) {
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
