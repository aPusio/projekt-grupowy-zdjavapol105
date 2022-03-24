package sda.training.rps.app;

import sda.training.rps.model.Player;

public interface IGameService {

    void startNewGame(Player player);

    void loadOldGame(Player player);

    void loadFinishedGames(Player player);

    void listAllGames();



}
