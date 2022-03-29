package sda.training.rps.app;


import sda.training.rps.dao.PlayerDao;
import sda.training.rps.model.Player;
import sda.training.rps.util.ScannerSingleton;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    private final PlayerDao playerDao = new PlayerDao();
    private final ScannerSingleton scanner = ScannerSingleton.getInstance();

    public Player choosePlayer() {
        String name = insertName();
        Optional<Player> optionalPlayer = playerDao.findByName(name);

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            System.out.println("Witaj "
                    + player.getName()
                    + "! Twój dotychczasowy wynik to: "
                    + player.getScore()
                    + ".\n");

            return player;
        } else {
            return createNewPlayer(name);
        }
    }

    private Player createNewPlayer(String name) {
        Player player = new Player(name);
        System.out.println("Witaj " + name + "! Miłej gry :)\n");
        return playerDao.mergeObject(player);
    }


    public void updatePlayer(Player player) {
        playerDao.mergeObject(player);
    }


    private String insertName() {
        System.out.print("Podaj swój nick: ");
        return scanner.scannerLine();
    }


    public void loadTopPlayers() {

        System.out.println("Top 10 najlepszych graczy:");
        List<Player> players = playerDao.findBest(10);
        players.forEach(player ->
                System.out.println((players.indexOf(player) + 1) + ". " + player)
        );
        backToMenu();
    }


    private void backToMenu() {
        System.out.println();
        System.out.print("Wciśnij ENTER by wrócić.");
        scanner.scannerLine();
    }


}
