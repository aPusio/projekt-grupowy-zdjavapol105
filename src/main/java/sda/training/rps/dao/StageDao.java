package sda.training.rps.dao;

import sda.training.rps.model.Game;
import sda.training.rps.model.Player;
import sda.training.rps.model.Stage;

import java.util.Collections;
import java.util.List;

public class StageDao implements IStageDao {
    @Override
    public Stage findById(int id) {
        return null;
    }

    @Override
    public void insertObject(Stage stage) {

    }

    @Override
    public List<Stage> findAllByGame(Game game) {
        return Collections.emptyList();
    }

    @Override
    public List<Stage> findAllByGameWinByPlayer(Game game) {
        return Collections.emptyList();
    }

    @Override
    public List<Stage> findAllByGameWinByComputer(Game game) {
        return Collections.emptyList();
    }

    @Override
    public List<Stage> findAllByPlayer(Player player) {
        return Collections.emptyList();
    }
}
