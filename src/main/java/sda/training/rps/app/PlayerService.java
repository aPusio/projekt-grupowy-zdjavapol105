package sda.training.rps.app;

import sda.training.rps.dao.IPlayerDao;
import sda.training.rps.dao.PlayerDao;
import sda.training.rps.model.Player;
import sda.training.rps.util.ScannerSingleton;

import java.util.List;

public class PlayerService implements IPlayerService {
    IPlayerDao playerDao = new PlayerDao();
    ScannerSingleton scanner = ScannerSingleton.getInstance();

    @Override
    public Player choosePlayer() {
        String name = insertName();
        Player player = playerDao.findByName(name);
        if (player != null) {
            return player;
        } else {
            return createNewPlayer(name);
        }
    }

    private Player createNewPlayer(String name) {
        Player player = new Player(name);
        return playerDao.mergeObject(player);
    }

    @Override
    public void updatePlayer(Player player) {
        playerDao.mergeObject(player);
    }


    private String insertName() {
        return null;
    }

    @Override
    public void loadTopPlayers() {
        System.out.println();
        List<Player> players = playerDao.findBest(10);
        // pamietaj by nie wyswietlac nulli
        players.forEach(System.out::println);
        backToMenu();
    }

    private void backToMenu() {
        System.out.print("");
        scanner.scannerLine();
    }


}
