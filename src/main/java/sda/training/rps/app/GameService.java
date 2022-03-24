package sda.training.rps.app;

import sda.training.rps.dao.GameDao;
import sda.training.rps.dao.IGameDao;
import sda.training.rps.dao.IStageDao;
import sda.training.rps.dao.StageDao;
import sda.training.rps.model.Game;
import sda.training.rps.model.Player;
import sda.training.rps.util.Result;
import sda.training.rps.util.ScannerSingleton;

import java.util.Collections;
import java.util.List;

public class GameService implements IGameService {
    private IGameDao gameDao = new GameDao();
    private IStageDao stageDao = new StageDao();
    private Game game = new Game();
    private IStageService stageService = new StageService();
    private ScannerSingleton scanner = ScannerSingleton.getInstance();

    @Override
    public void startNewGame(Player player) {
        int winStagesNo = loadStagesProperty();
        game = createGame(player, winStagesNo);
        loopStages();

        /*      wczytuje od gracz i ustawia ilosc wygranych etapow
         *      Twozy instancje gry
         *      petla stagy
         * */
    }

    private int loadStagesProperty() {
        return 0;
    }

    private Game createGame(Player player, int winStagesNo) {
        game.setWinStagesNo(winStagesNo);
        game.setPlayer(player);
        //ustawienie poczatku gry
        return gameDao.mergeObject(game);
    }

    private void loopStages() {
        Result result;
        boolean continueGame = true;
        while (continuationGame(continueGame)) {
            result = stageService.playStage(game);
            addPoints(result);
            continueGame = false;
        }
        //ustawienie wyniku gry
        //ustawienie zakonczenia gry (if result win/lose - data zakonczenia)
        gameDao.mergeObject(game);
    }

    private boolean continuationGame(boolean continueGame) {
        return game.getPlayerScore() < game.getWinStagesNo() ||
                game.getComputerScore() < game.getWinStagesNo() ||
                continueGame;
    }

    private void addPoints(Result result) {
    }

    @Override
    public void loadOldGame(Player player) {
        showCustomeMessage();
        List<Game> games = loadListOfOldGames(player);
        if (games != null) {
            games.forEach(System.out::println);
        } else {
            System.out.println();
            return;
        }
        int choice = getPlayerChoice(games.size());
        game = games.get(choice);
        if (game.getPlayerScore() == 0 &&
                game.getComputerScore() == 0) {
            loadScores();
        }
        loopStages();
    }

    private void showCustomeMessage() {
    }


    private List<Game> loadListOfOldGames(Player player) {
        return Collections.emptyList();
    }


    private int getPlayerChoice(int size) {
        return 0;
    }

    private void loadScores() {
        game.setPlayerScore(stageDao.findAllByGameWinByPlayer(game).size());
        game.setComputerScore(stageDao.findAllByGameWinByComputer(game).size());
    }

    @Override
    public void loadFinishedGames(Player player) {
        List<Game> games = gameDao.findAllOfPlayer(player);
        // pamietaj by nie wyswietlac nulli
        games.forEach(System.out::println);
        backToMenu();
    }

    private void backToMenu() {
        System.out.print("");
        scanner.scannerLine();
    }

    @Override
    public void listAllGames() {
        int page = 1;
        while (page != 0) {
            List<Game> games = gameDao.findAll(10, ((page * 10) - 10));
            // pamietaj by nie wyswietlac nulli
            games.forEach(System.out::println);
            page = controlListAllGames(page);
        }
    }

    private int controlListAllGames(int page) {
        // if (page == 1) else if (page == (int)Math.ceil((gameDao.countGames() / 10.0)) else;
        // scanner.scannerInt();
        // case page

        return 0;
    }

}
