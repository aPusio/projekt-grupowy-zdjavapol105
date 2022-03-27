package sda.training.rps.app;

import sda.training.rps.dao.IPlayerDao;
import sda.training.rps.dao.PlayerDao;
import sda.training.rps.model.Player;
import sda.training.rps.util.ScannerSingleton;

import java.util.List;

public class PlayerService implements IPlayerService {
    private IPlayerDao playerDao = new PlayerDao();
    private ScannerSingleton scanner = ScannerSingleton.getInstance();


    @Override
    public Player choosePlayer() {
        String name = insertName();
        Player player = playerDao.findByName(name);
        if (player != null) {
            System.out.println("Witaj "
                    + player.getName()
                    + "! Twój dotychczasowy wynik to: "
                    + player.getScore()
                    + ".");
            return player;
        } else {
            return createNewPlayer(name);
        }
    }

    private Player createNewPlayer(String name) {
        Player player = new Player(name);
        System.out.println("Witaj " + name + "! Miłej gry :)");
        return playerDao.mergeObject(player);
    }

    @Override
    public void updatePlayer(Player player) {
        playerDao.mergeObject(player);
    }


    private String insertName() {
        System.out.print("Podaj swój nick: ");
        return scanner.scannerLine();
    }

    @Override
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
        System.out.println("Wprowadź dowolny znak by powrócić.");
        scanner.scannerLine();
    }


}
