package sda.training.angry_nerds_game.game;

import java.util.HashSet;
import java.util.Set;

public class GameBoard {

    int[][] gameBoard = SingletonGameConfig.getInstance().getGameBoard();


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


    public boolean checkConflict(Set<Point> shot, Set<Point> target) {
        Set<Point> union = new HashSet<>(shot);
        union.retainAll(target);
        if (union.size() > 0) {
            System.out.println("Trafiony !!! ");
            return true;
        }
        else {
            System.out.println("Chybiłeś :( ");
            return false;
        }
    }
}
