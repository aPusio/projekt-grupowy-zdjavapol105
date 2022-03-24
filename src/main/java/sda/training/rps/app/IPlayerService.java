package sda.training.rps.app;

import sda.training.rps.model.Player;

public interface IPlayerService {
    Player choosePlayer();

    void updatePlayer(Player player);

    void loadTopPlayers();
}
