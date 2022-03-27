package sda.training.rps.app;

import sda.training.rps.model.Player;

public interface IGameService {

    void startNewGame(Player player);

    void loadOldGame(Player player);

    void loadFinishedGamesOfPlayer(Player player);

    void loadFinishedGames();



}
