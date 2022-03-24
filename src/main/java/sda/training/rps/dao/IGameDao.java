package sda.training.rps.dao;

import sda.training.rps.model.Game;
import sda.training.rps.model.Player;

import java.util.List;

public interface IGameDao {
    Game findById(int id);

    Game mergeObject(Game game);

    int countGames();

    List<Game> findAll(int maxResults, int firstResult);

    List<Game> findAllOfPlayer(Player player);

    List<Game> findAllNotCompleteOfPlayer(Player player);



}
