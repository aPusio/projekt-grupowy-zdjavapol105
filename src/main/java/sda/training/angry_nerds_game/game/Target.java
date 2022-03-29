package sda.training.angry_nerds_game.game;

import sda.training.angry_nerds_game.schema.AngryNerdsDBShot;

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

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
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

    private void generateSizeXY(){
        Random random = new Random();

        xSize = random.nextInt( 20)+20;
        ySize = random.nextInt(6)+3;

    }

    private void generatePositionXY(){

        Random random = new Random();

        xPosition = random.nextInt(SingletonGameConfig.getInstance().getMaxX()-xSize-50)+50;
        yPosition = random.nextInt(SingletonGameConfig.getInstance().getMaxY()-ySize-5)+5;

    }


}
