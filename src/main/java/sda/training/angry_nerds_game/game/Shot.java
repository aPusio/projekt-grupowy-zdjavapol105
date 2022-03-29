package sda.training.angry_nerds_game.game;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Shot {

    private Set<Point> shot = new HashSet<>();

    int shotX;

    public int getShotX() {
        return shotX;
    }

    public int getShotY() {
        return shotY;
    }

    int shotY;

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

    public void enterShotValues() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj Paramerty Strzału X: ");
        while (true) {
            try {
                shotX = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawne wartości ...");
            }
            scanner.next();
        }
        System.out.print("Podaj Paramerty Strzału Y: ");
        while (true) {
            try {
                shotY = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawne wartości ...");
            }
            scanner.next();
        }
        scanner.nextLine();
        System.out.println("Paramerty strzału X= " + shotX + " Y= " + shotY);
    }

    //TODO zrobić ciągłą shotline

    //TODO Karol zaproponował rodzaj broni (zróbmy na początek wieksze pociski)

}
