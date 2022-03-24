package sda.training.rps.dao;

import sda.training.rps.model.Player;

import java.util.List;

public interface IPlayerDao {

    Player findById(int id);

    Player findByName(String name);

    Player mergeObject(Player player);

    List<Player> findBest(int topNumber);

    List<Player> findAll();


}
