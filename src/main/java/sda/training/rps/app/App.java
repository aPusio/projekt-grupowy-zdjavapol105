package sda.training.rps.app;

import sda.training.rps.model.MainMenu;
import sda.training.rps.model.Player;
import sda.training.rps.util.ScannerSingleton;

public class App implements IApp {
    private Player player;
    private IPlayerService playerService = new PlayerService();
    private IGameService gameService = new GameService();
    private ScannerSingleton scanner = ScannerSingleton.getInstance();

    @Override
    public void startRPS() {
        showOpeningCredits();
        player = playerService.choosePlayer();
        runMenu();
    }

    private void showOpeningCredits() {
    }

    private void runMenu() {
        boolean continueGame = true;
        while (continueGame) {
            showMenu();
            continueGame = getPlayersChoice();
        }
    }

    private void showMenu() {
        System.out.println("Witaj " + player.getName() + "!");
        System.out.println();
        System.out.println("1. Zacznij grę.");
        System.out.println("2. Wczytaj niedokończoną grę.");
        System.out.println("3. Top 10 graczy.");
        System.out.println("4. Pokaż wszystkie, skończone gry.");
        System.out.println("5. Pokaż wszystkie, ukończone, twoje gry.");
        System.out.println("6. Zmień użytkownika");
        System.out.println("7. Wyjdź z Papier-Kamień-Nożyczki");
    }

    private boolean getPlayersChoice() {
        MainMenu option = MainMenu.getOptionByIndex(scanner.scannerInt());

        switch (option) {
            case NEW_GAME:
                gameService.startNewGame(player);
                break;
            case LOAD_GAME:
                gameService.loadOldGame(player);
                break;
            case TOP10:
                playerService.loadTopPlayers();
                break;
            case ALL_GAMES:
                gameService.loadFinishedGames();
                break;
            case YOUR_GAMES:
                gameService.loadFinishedGamesOfPlayer(player);
                break;
            case CHANGE_USER:
                player = playerService.choosePlayer();
                break;
            case QUIT:
                return false;
            default:
                break;
        }

        return true;
    }


}
