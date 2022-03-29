package sda.training.rps.app;

import sda.training.rps.dao.GameDao;
import sda.training.rps.dao.StageDao;
import sda.training.rps.model.Game;
import sda.training.rps.model.Player;
import sda.training.rps.util.Result;
import sda.training.rps.util.ScannerSingleton;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

public class GameService {
    private final GameDao gameDao = new GameDao();
    private final StageDao stageDao = new StageDao();
    private Game game;
    private final PlayerService playerService;
    private final StageService stageService;
    private final ScannerSingleton scanner = ScannerSingleton.getInstance();

    public GameService(PlayerService playerService) {
        this.playerService = playerService;
        this.stageService = new StageService(playerService);
    }

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
        game = new Game();
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
            System.out.println("Ty: " + game.getPlayerScore() + " \tPrzeciwnik: " + game.getComputerScore());
            if (isContinuable(true)) {
                continueGame = keepPlaying();
            }
        }
        if (Objects.equals(game.getPlayerScore(), game.getWinStagesNo())) {
            game.setResult(Result.WIN);
        }
        if (Objects.equals(game.getComputerScore(), game.getWinStagesNo())) {
            game.setResult(Result.LOSE);
        }
        if (isFinished()) {
            game.setEndDate(LocalDateTime.now());
        }

        if (game.getResult() == null) {
            System.out.println("Gra chwilowo przerwana.");
        } else {
            System.out.println("Gra skończona z wynikiem " + game.getResult());
        }

        playerService.updatePlayer(game.getPlayer());
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
        return game.getPlayerScore() < game.getWinStagesNo() &&
                game.getComputerScore() < game.getWinStagesNo() &&
                continueGame;
    }

    private void addPoints(Result result) {
        if (addPointsConditions(result)) {
            game.setPlayerScore(game.getPlayerScore() + result.getPlayerPoint());
            game.setComputerScore(game.getComputerScore() + result.getComputerPoint());
        }
    }

    private boolean addPointsConditions(Result result) {
        return result != null && game.getPlayerScore() != null && game.getComputerScore() != null;
    }

    private boolean isFinished() {
        return game.getResult() != null;
    }


    public void loadOldGame(Player player) {
        showCustomeMessage();
        List<Game> games = gameDao.findAllNotCompleteOfPlayer(player);

        if (!games.isEmpty()) {
            games.forEach(this::loadScores);
            games.forEach(oldGame ->
                    System.out.println((games.indexOf(oldGame) + 1)
                            + ".\t"
                            + printGame(oldGame))
            );
        } else {
            System.out.println("Brak niedokończonych gier");
            return;
        }

        int choice = getPlayerChoice(games.size());
        if (choice == -1) {
            return;
        }
        game = games.get(choice);

        System.out.println("Wybrano grę:");
        System.out.println(printGame(game));

        loopStages();
    }

    private String printGame(Game game) {
        return "Ilość koniecznych wygranych: " + game.getWinStagesNo()
                + " Punkty gracza: " + game.getPlayerScore()
                + " Punkty komputera: " + game.getComputerScore()
                + " Data rozpoczęćia: " + game.getStartDate();
    }

    private void showCustomeMessage() {
        System.out.println("Lista niedokończonmych gier\n" +
                "Wprowadź numer gry którą chcesz kontynuować");

    }

    private int getPlayerChoice(int size) {
        System.out.print("Wybierz którą grę chcesz wczytać. Wprowadź 0 by cofnąć do poprzedniego menu: ");

        int choice = scanner.scannerInt();
        if (choice <= 0 || choice > size + 1) {
            return -1;
        } else {
            return choice - 1;
        }
    }

    private void loadScores(Game oldGame) {
        oldGame.setPlayerScore(stageDao.findAllByGameWinByPlayer(oldGame).size());
        oldGame.setComputerScore(stageDao.findAllByGameWinByComputer(oldGame).size());
    }


    public void loadFinishedGamesOfPlayer(Player player) {
        int page = 1;
        while (page != 0) {
            List<Game> games = gameDao.findAllFinishedOfPlayer(10, ((page * 10) - 10), player);

            if (games.isEmpty()) {
                System.out.println("Brak ukończonych gier przez gracza: " + player.getName());
                backToMenu();
                return;
            }

            listLoadedGames(page, games);
            page = controlListAllGames(page, player);
        }
    }

    private void backToMenu() {
        System.out.println();
        System.out.print("Wciśnij ENTER by wrócić.");
        scanner.scannerLine();
    }


    public void loadFinishedGames() {
        int page = 1;
        while (page != 0) {
            List<Game> games = gameDao.findAllFinished(10, ((page * 10) - 10));

            if (games.isEmpty()) {
                System.out.println("Brak ukończonych gier.");
                backToMenu();
                return;
            }

            listLoadedGames(page, games);
            page = controlListAllGames(page);
        }
    }

    private void listLoadedGames(int page, List<Game> games) {
        for (Game game : games) {
            System.out.println((games.indexOf(game) + 1 + 10 * (page - 1)) + ".\t"
                    + "Gra o ID: " + game.getId()
                    + " Gracz: " + game.getPlayer().getName()
                    + " Ilość koniecznych wygranych: " + game.getWinStagesNo()
                    + " Punkty gracza: " + game.getPlayerScore()
                    + " Punkty komputera: " + game.getComputerScore()
                    + " Wynik: " + game.getResult()
                    + " Data zakończenia: " + game.getEndDate()
                    + " Łączny czas gry: " + getTime(game)
            );
        }
    }

    private String getTime(Game game) {
        long second;
        if (game.getEndDate() == null || game.getStartDate() == null) {
            second = 0;
        } else {
            second = game.getEndDate().toEpochSecond(ZoneOffset.UTC) - game.getStartDate().toEpochSecond(ZoneOffset.UTC);
        }
        int day = 0;
        int hour = 0;
        int minute = 0;

        if (second > 59) {
            minute = (int) second / 60;
            second = (int) second % 60;
        }
        if (minute > 59) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if (hour > 23) {
            day = hour / 24;
            hour = hour % 24;
        }

        return day + "d:" + hour + "h:" + minute + "m:" + second + "s";
    }

    private int controlListAllGames(int page, Player player) {
        int gamesCountAfterRound = (int) Math.ceil((gameDao.countFinishedGamesOfPlayer(player) / 10.0));
        if (page == 1 && gamesCountAfterRound == 1) {
            System.out.println("        0 - cofnij");
            scanner.scannerInt();
            return 0;
        } else if (page == 1) {
            System.out.println("        0 - cofnij      2>>");
            if (scanner.scannerInt() == 2) {
                return ++page;
            }
            return 0;
        } else if (page < gamesCountAfterRound) {
            System.out.println("<<1     0 - cofnij      2>>");
            switch (scanner.scannerInt()) {
                case 1:
                    return --page;
                case 2:
                    return ++page;
                default:
                    return 0;
            }
        } else {
            System.out.println("<<1     0 - cofnij     ");
            if (scanner.scannerInt() == 1) {
                return --page;
            }
            return 0;
        }
    }

    private int controlListAllGames(int page) {
        int gamesCountAfterRound = (int) Math.ceil((gameDao.countFinishedGames() / 10.0));
        if (page == 1 && gamesCountAfterRound == 1) {
            System.out.println("        0 - cofnij");
            scanner.scannerInt();
            return 0;
        } else if (page == 1) {
            System.out.println("        0 - cofnij      2>>");
            if (scanner.scannerInt() == 2) {
                return ++page;
            }
            return 0;
        } else if (page < gamesCountAfterRound) {
            System.out.println("<<1     0 - cofnij      2>>");
            switch (scanner.scannerInt()) {
                case 1:
                    return --page;
                case 2:
                    return ++page;
                default:
                    return 0;
            }
        } else {
            System.out.println("<<1     0 - cofnij     ");
            if (scanner.scannerInt() == 1) {
                return --page;
            }
            return 0;
        }
    }

}
