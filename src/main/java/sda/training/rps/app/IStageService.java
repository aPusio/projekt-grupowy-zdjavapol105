package sda.training.rps.app;

import sda.training.rps.model.Game;
import sda.training.rps.util.Result;

public interface IStageService {
    Result playStage(Game game);
}
