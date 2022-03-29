package sda.training.angry_nerds_game.game;

import java.util.concurrent.TimeUnit;

public class SinglePlayerGame {

    public void startGame() {

        AngryNerdsPlayer actualPlayer;
        CopyToDBClass copyToDBClass = new CopyToDBClass();
        ConsoleScreen consoleScreen = new ConsoleScreen();
        GameBoard gameBoard = new GameBoard();
        ActualGamePlayers players = new ActualGamePlayers();
        Point sonda = new Point(0, 0, 'S');
        Target target = new Target();
        Shot shot = new Shot();

        players.addAngryNerdsPlayer();

        target.initBoxTarget();
        gameBoard.clearBoard();

        showScreen(gameBoard, sonda, target, shot, consoleScreen);
        actualPlayer = players.getPlayers().peek();

        while (actualPlayer.getLife()>0) {

            actualPlayer = players.getPlayers().poll();
            System.out.println(actualPlayer.getName()+" Pozostało Ci jeszcze :"+actualPlayer.getLife()+" żyć. Twój wynik ="+actualPlayer.getScore()+" trafień");

            shot.enterShotValues();

            showScreen(gameBoard, sonda, target, shot, consoleScreen);

            shot.genarateShotLine(shot.getShotX(), shot.getShotY(), 0, 1);

            showScreen(gameBoard, sonda, target, shot, consoleScreen);

            copyToDBClass.copyValuesToAngryNerdsDBShot(shot,target,actualPlayer);

            if(gameBoard.checkConflict(shot.getShot(), target.getTarget())){
                actualPlayer.setLife(actualPlayer.getLife()+1);
                actualPlayer.setScore(actualPlayer.getScore()+1);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                target.initBoxTarget();
                shot.getShot().clear();

                gameBoard.clearBoard();
                showScreen(gameBoard, sonda, target, shot, consoleScreen);

            }
            else actualPlayer.setLife(actualPlayer.getLife()-1);

            gameBoard.clearBoard();
            gameBoard.setPoints(target.getTarget());
            shot.getShot().clear();
            players.getPlayers().add(actualPlayer);
        }

        System.out.println(actualPlayer.getName()+" Koniec Gry. Twój wynik ="+actualPlayer.getScore()+" trafień");

        //TODO : w tym miejscu mozna wysłac dane do bazy danych o graczu i aktualnym wyniku ...
    }

    private void showScreen(GameBoard gameBoard, Point sonda, Target target, Shot shot, ConsoleScreen consoleScreen) {
        gameBoard.setPoint(sonda);
        gameBoard.setPoints(target.getTarget());
        gameBoard.setPoints(shot.getShot());
        if(!SingletonGameConfig.getInstance().color) consoleScreen.showScreen(gameBoard.gameBoard);
        else consoleScreen.showColorScreen(gameBoard.gameBoard);
    }
}
