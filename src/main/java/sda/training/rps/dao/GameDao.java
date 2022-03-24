package sda.training.rps.dao;

import sda.training.rps.model.Game;
import sda.training.rps.model.Player;

import java.util.Collections;
import java.util.List;

public class GameDao implements IGameDao {
    @Override
    public Game findById(int id) {
        return null;
    }

    @Override
    public Game mergeObject(Game game) {
    return null;
    }

    @Override
    public int countGames() {
        return 0;
    }

    @Override
    public List<Game> findAll(int maxResults, int firstResult) {
        return Collections.emptyList();
    }

    @Override
    public List<Game> findAllOfPlayer(Player player) {
        return Collections.emptyList();
    }

    @Override
    public List<Game> findAllNotCompleteOfPlayer(Player player) {
        return Collections.emptyList();
    }
}
