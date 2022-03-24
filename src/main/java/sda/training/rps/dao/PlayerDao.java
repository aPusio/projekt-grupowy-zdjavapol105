package sda.training.rps.dao;

import sda.training.rps.model.Player;

import java.util.Collections;
import java.util.List;

public class PlayerDao implements IPlayerDao {
    @Override
    public Player findById(int id) {
        return null;
    }

    @Override
    public Player findByName(String name) {
        return null;
    }

    @Override
    public Player mergeObject(Player player) {
        return null;
    }

    @Override
    public List<Player> findBest(int topNumber) {
        return Collections.emptyList();
    }

    @Override
    public List<Player> findAll() {
        return Collections.emptyList();
    }
}
