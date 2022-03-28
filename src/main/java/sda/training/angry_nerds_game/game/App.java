package sda.training.angry_nerds_game.game;

import sda.training.angry_nerds_game.schema.AngryNerdsPlayer;
import sda.training.angry_nerds_game.schema.AngryNerdsShot;


public class App {

    public static void main(String[] args) {

        AngryNerdsPlayer actualPlayer;

        ConsoleScreen consoleScreen = new ConsoleScreen();

        GameBoard gameBoard = new GameBoard();
        gameBoard.clearBoard();

        Point sonda = new Point(0,0,'S');
        gameBoard.setPoint(sonda);


        //TODO zrobić losowanie pozycji i wielkosci celu

        Target target = new Target();
        target.initBoxTarget(100,10,10,5,'T');


        gameBoard.setPoints(target.getTarget());
        consoleScreen.showColorScreen(gameBoard.gameBoard);

        ActualGamePlayers players = new ActualGamePlayers();
        players.initPlayers();

        Shot shot = new Shot();
        AngryNerdsShot angryNerdsShot = new AngryNerdsShot();

        while(true){

            // TODO okreslić jak ma wyglądac rozgrywka i parametry wyjscia z pętli

            actualPlayer = players.getPlayers().poll();
            System.out.println(actualPlayer.getName());

            angryNerdsShot.enterShotValues();

            shot.genarateShotLine(angryNerdsShot.getShotX(), angryNerdsShot.getShotY(), 0,1);
            gameBoard.setPoints(shot.getShot());

            //consoleScreen.showScreen(gameBoard.gameBoard);
            consoleScreen.showColorScreen(gameBoard.gameBoard);

            gameBoard.checkConflict(shot.getShot(),target.getTarget());

            //TODO : w tym miejscu mozna wysłac dane do bazy danych o graczu targecie shocie ...

            gameBoard.clearBoard();
            gameBoard.setPoints(target.getTarget());
            shot.getShot().clear();
            players.getPlayers().add(actualPlayer);
        }
    }
}
