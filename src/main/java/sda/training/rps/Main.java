package sda.training.rps;

import sda.training.rps.app.GameService;
import sda.training.rps.app.IGameService;
import sda.training.rps.dao.GameDao;
import sda.training.rps.dao.PlayerDao;
import sda.training.rps.dao.StageDao;

public class Main {
    //Klasa Main przeznaczona do testowania aplikacji. Planowana do kasacji po zakończeniu projektu
    public static void main(String[] args) {

//        PlayerService playerApp = new PlayerService();
//        Player player = playerApp.choosePlayer();
//        System.out.println(player.getName());
//        playerApp.updatePlayer(player);
//        System.out.println(player.getName());
//        GameDao gameDao = new GameDao();
//        int test = (int)Math.ceil((67/ 10.0));
//        System.out.println( test);

        PlayerDao playerDao = new PlayerDao();
        //System.out.println(playerDao.mergeObject(new Player("CrySis")).getId());
        // playerDao.findBest(5).forEach(System.out::println);
        GameDao gameDao = new GameDao();
//        System.out.println(gameDao.countGames());
//        gameDao.findAllNotCompleteOfPlayer(playerDao.findById(1)).forEach(System.out::println);
        StageDao stageDao = new StageDao();
        //stageDao.insertObject(new Stage(gameDao.findById(1), Move.PAPER, Move.ROCK, Result.WIN));
//        stageDao.findAllByGame(gameDao.findById(1)).forEach(System.out::println);
//        System.out.println("====================");
//        stageDao.findAllByGameWinByPlayer(gameDao.findById(1)).forEach(System.out::println);
//        System.out.println("====================");
//        stageDao.findAllByGameWinByComputer(gameDao.findById(1)).forEach(System.out::println);
//        System.out.println("====================");
//        stageDao.findAllByPlayer(playerDao.findById(1)).forEach(System.out::println);

        IGameService gameService = new GameService();
      // gameService.loadOldGame(playerDao.findById(1));
       gameService.loadFinishedGamesOfPlayer(playerDao.findById(1));
    }
}
