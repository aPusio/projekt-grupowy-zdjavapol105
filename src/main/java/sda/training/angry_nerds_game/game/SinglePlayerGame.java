package sda.training.angry_nerds_game.game;

import sda.training.angry_nerds_game.schema.AngryNerdsPlayer;
import sda.training.angry_nerds_game.schema.AngryNerdsShot;

public class SinglePlayerGame {


    public void startGame() {

        AngryNerdsPlayer actualPlayer;

        ConsoleScreen consoleScreen = new ConsoleScreen();

        GameBoard gameBoard = new GameBoard();
        gameBoard.clearBoard();

        ActualGamePlayers players = new ActualGamePlayers();
        players.addAngryNerdsPlayer();


        Point sonda = new Point(0, 0, 'S');
        gameBoard.setPoint(sonda);


        //TODO zrobić losowanie pozycji i wielkosci celu

        Target target = new Target();
        target.initBoxTarget();


        Shot shot = new Shot();
        AngryNerdsShot angryNerdsShot = new AngryNerdsShot();

        gameBoard.setPoints(target.getTarget());

        if(!SingletonGameConfig.getInstance().color) consoleScreen.showScreen(gameBoard.gameBoard);
        else consoleScreen.showColorScreen(gameBoard.gameBoard);

        int life =5;

        while (life>0) {

            // TODO okreslić jak ma wyglądac rozgrywka i parametry wyjscia z pętli

            actualPlayer = players.getPlayers().poll();
            System.out.println(actualPlayer.getName()+" Pozostało Ci jeszcze :"+life+" żyć");

            angryNerdsShot.enterShotValues();

            shot.genarateShotLine(angryNerdsShot.getShotX(), angryNerdsShot.getShotY(), 0, 1);
            gameBoard.setPoints(shot.getShot());

            if(!SingletonGameConfig.getInstance().color) consoleScreen.showScreen(gameBoard.gameBoard);
            else consoleScreen.showColorScreen(gameBoard.gameBoard);

            if(gameBoard.checkConflict(shot.getShot(), target.getTarget())){
             life++;
             target.initBoxTarget();

                gameBoard.clearBoard();
                gameBoard.setPoints(target.getTarget());
                if(!SingletonGameConfig.getInstance().color) consoleScreen.showScreen(gameBoard.gameBoard);
                else consoleScreen.showColorScreen(gameBoard.gameBoard);
            }
            else life--;

            //TODO : w tym miejscu mozna wysłac dane do bazy danych o graczu targecie shocie ...

            gameBoard.clearBoard();
            gameBoard.setPoints(target.getTarget());
            shot.getShot().clear();
            players.getPlayers().add(actualPlayer);
        }
    }
}
