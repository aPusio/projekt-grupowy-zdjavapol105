package sda.training.rps.dao;

import sda.training.rps.model.Game;
import sda.training.rps.model.Player;

import java.util.List;

public interface IGameDao {
    Game findById(int id);

    Game mergeObject(Game game);

    int countFinishedGames();

    int countFinishedGamesOfPlayer(Player player);

    List<Game> findAllFinished(int maxResults, int firstResult);

    List<Game> findAllFinishedOfPlayer(int maxResults, int firstResult, Player player);

    List<Game> findAllNotCompleteOfPlayer(Player player);



}
