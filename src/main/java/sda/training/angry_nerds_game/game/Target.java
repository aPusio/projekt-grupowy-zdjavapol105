package sda.training.angry_nerds_game.game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Target {

    private Set<Point> target = new HashSet<>();

    private int xPosition;
    private int yPosition;
    private int xSize;
    private int ySize;
    private char symbol;


    public Set<Point> getTarget() {
        return target;
    }

    public void initBoxTarget(){

        target.clear();
        generateSizeXY();
        generatePositionXY();

        for (int i = yPosition; i < yPosition+ySize; i++) {
            for (int j = xPosition; j < xPosition+xSize; j++) {
                this.target.add(new Point(j,i,'T'));
            }
        }
    }

    public void generateSizeXY(){
        Random random = new Random();

        xSize = random.nextInt(20,80);
        ySize = random.nextInt(3,9);
    }

    public void generatePositionXY(){

        Random random = new Random();

        xPosition = random.nextInt(50,SingletonGameConfig.getInstance().getMaxX()-xSize);
        yPosition = random.nextInt(5,SingletonGameConfig.getInstance().getMaxY())-ySize;

    }



}
