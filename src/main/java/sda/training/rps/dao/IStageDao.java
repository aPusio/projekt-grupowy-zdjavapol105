package sda.training.rps.dao;

import sda.training.rps.model.Game;
import sda.training.rps.model.Player;
import sda.training.rps.model.Stage;

import java.util.List;

public interface IStageDao {
    Stage findById(int id);

    void insertObject(Stage stage);

    List<Stage> findAllByGame(Game game);

    List<Stage> findAllByGameWinByPlayer(Game game);

    List<Stage> findAllByGameWinByComputer(Game game);

    List<Stage> findAllByPlayer(Player player);
}
