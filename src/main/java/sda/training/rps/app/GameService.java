package sda.training.rps.app;

import sda.training.rps.dao.GameDao;
import sda.training.rps.dao.IGameDao;
import sda.training.rps.dao.IStageDao;
import sda.training.rps.dao.StageDao;
import sda.training.rps.model.Game;
import sda.training.rps.model.Player;
import sda.training.rps.util.Result;
import sda.training.rps.util.ScannerSingleton;

import java.time.LocalDateTime;
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
    }

    private int loadStagesProperty() {
        System.out.print("Podaj ile punktów potrzeba na wygraną: ");
        return scanner.scannerInt();
    }

    private Game createGame(Player player, int winStagesNo) {
        game.setWinStagesNo(winStagesNo);
        game.setPlayer(player);
        game.setComputerScore(0);
        game.setPlayerScore(0);
        game.setStartDate(LocalDateTime.now());

        return gameDao.mergeObject(game);
    }

    private void loopStages() {
        Result result;
        boolean continueGame = true;
        while (isContinuable(continueGame)) {
            result = stageService.playStage(game);
            addPoints(result);
            continueGame = keepPlaying();
        }
        if (game.getPlayerScore() == game.getWinStagesNo()) {
            game.setResult(Result.WIN);
        }
        if (game.getComputerScore() == game.getWinStagesNo()) {
            game.setResult(Result.LOSE);
        }
        if (isFinished()) {
            game.setEndDate(LocalDateTime.now());
        }

        gameDao.mergeObject(game);
    }

    private boolean keepPlaying() {
        System.out.print("Czy chcesz kontynuować?(y/n): ");
        String playerChoice = scanner.scannerLine();
        switch (playerChoice.toLowerCase()) {
            case "n":
            case "no":
            case "nie":
                return false;
            default:
                return true;
        }


    }

    private boolean isContinuable(boolean continueGame) {
        return game.getPlayerScore() < game.getWinStagesNo() ||
                game.getComputerScore() < game.getWinStagesNo() ||
                continueGame;
    }

    private void addPoints(Result result) {
        game.setPlayerScore(game.getPlayerScore() + result.getPlayerPoint());
        game.setComputerScore(game.getComputerScore() + result.getComputerPoint());
    }

    private boolean isFinished() {
        return game.getResult().equals(Result.WIN) || game.getResult().equals(Result.LOSE);
    }

    @Override
    public void loadOldGame(Player player) {
        showCustomeMessage();
        List<Game> games = gameDao.findAllNotCompleteOfPlayer(player);
        if (!games.isEmpty()) {
            games.forEach(game -> System.out.println((games.indexOf(game) + 1) + "."
                    + " Gra o ID: " + game.getId()
                    + " Ilość koniecznych wygranych: " + game.getWinStagesNo()
                    + " Punkty gracza: " + game.getPlayerScore()
                    + " Punkty komputera: " + game.getComputerScore()
                    + " Data rozpoczęćia: " + game.getStartDate()

            ));
        } else {
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
