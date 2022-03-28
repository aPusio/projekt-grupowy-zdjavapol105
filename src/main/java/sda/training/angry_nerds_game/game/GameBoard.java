package sda.training.angry_nerds_game.game;

import java.util.HashSet;
import java.util.Set;

public class GameBoard {

    private final static int MAX_X = 181;
    private final static int MAX_Y = 31;

    int[][] gameBoard = new int[MAX_X][MAX_Y];


    public void setPoint(Point point) {
        for (int i = 0; i < gameBoard[0].length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (i == point.getPositionY() && j == point.getPositionX()) gameBoard[j][i] = point.getSymbol();
            }
        }
    }

    public void clearBoard() {
        for (int i = 0; i < gameBoard[0].length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[j][i] = (int) '.';
            }
        }
    }

    public void setPoints(Set<Point> points) {
        points.stream().forEach(n -> setPoint(n));
    }


    public void checkConflict(Set<Point> shot, Set<Point> target) {
        Set<Point> iloczyn = new HashSet<>(shot);
        iloczyn.retainAll(target);
        if (iloczyn.size() > 0) System.out.println("Trafiony !!! ");
        else System.out.println("Chybiłeś :( ");
    }


}
