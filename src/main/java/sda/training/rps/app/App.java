package sda.training.rps.app;

import sda.training.rps.model.Player;
import sda.training.rps.util.MainMenu;
import sda.training.rps.util.ScannerSingleton;

public class App {
    private Player player;
    private final PlayerService playerService = new PlayerService();
    private final GameService gameService = new GameService(playerService);
    private final ScannerSingleton scanner = ScannerSingleton.getInstance();

    public void startRPS() {
        showOpeningCredits();
        player = playerService.choosePlayer();
        runMenu();
    }

    private void showOpeningCredits() {
        System.out.println("Witaj w grze \"Papier-Kamień-Nożyczki\"\n" +
                "Przemek i Mateusz życzą miłej gry.");
    }

    private void runMenu() {
        boolean continueGame = true;
        while (continueGame) {
            showMenu();
            continueGame = getPlayersChoice();
        }
    }

    private void showMenu() {
        System.out.println("1. Wyświetl zasady gry.");
        System.out.println("2. Zacznij grę.");
        System.out.println("3. Wczytaj niedokończoną grę.");
        System.out.println("4. Top 10 graczy.");
        System.out.println("5. Pokaż wszystkie, skończone gry.");
        System.out.println("6. Pokaż wszystkie, ukończone, twoje gry.");
        System.out.println("7. Zmień użytkownika");
        System.out.println("8. Wyjdź z Papier-Kamień-Nożyczki");
    }

    private boolean getPlayersChoice() {
        MainMenu option = MainMenu.getOptionByIndex(scanner.scannerInt());

        switch (option) {
            case RULES:
                rules();
                break;
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

    private void rules() {
        System.out.println("Tu powinny być bardzo skomplikowane zasady gry.\n" +
                "Nie mieliśmy czasu ich zaimplementować.\n" +
                "Wierzymy, że znasz zasady tej prostej gry.\n" +
                "Trzymaj link do działania poszczególnych ruchów:\n" +
                "https://www.umop.com/images/hands.jpg\n");
    }


}
