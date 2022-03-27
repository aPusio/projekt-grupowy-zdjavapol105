package sda.training.rps.app;

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
        choosePlayer();
       // runMenu();
    }

    private void showOpeningCredits() {
    }

    private void choosePlayer() {
        player = playerService.choosePlayer();
    }

    private void runMenu() {
        boolean continueGame = true;
        while (continueGame) {
            showMenu();
            continueGame = getPlayersChoice();
        }
    }

    private void showMenu() {
    }

    private boolean getPlayersChoice() {


      /*  1. Zacznij gre startNewGame(player)
          2  Wczytaj gre loadOldGame(player)
          3. Top 10 loadTopPlayers()
          4. Ostatnie gry listAllGames()
          5. Pokaż gry danego gracza  loadFinishedGames(player)
          6. Zmien uzytkownia choosePlayer()
          7. Wyjdz z gry return false;
       */
        return true;
    }


}
