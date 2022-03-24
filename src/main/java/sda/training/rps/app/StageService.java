package sda.training.rps.app;

import sda.training.rps.dao.IStageDao;
import sda.training.rps.dao.StageDao;
import sda.training.rps.model.Game;
import sda.training.rps.model.Stage;
import sda.training.rps.util.Move;
import sda.training.rps.util.Result;
import sda.training.rps.util.ScannerSingleton;

public class StageService implements IStageService {
    IStageDao stageDao = new StageDao();
    Move playerMove;
    Move computerMove;
    private Result[][] outcome;
    ScannerSingleton scanner = ScannerSingleton.getInstance();

    @Override
    public Result playStage(Game game) {
        playerMove = getPlayerMove();
        computerMove = getComputerMove();
        Result result = getResult();
        saveStage(game, result);
        return result;
    }

    private void saveStage(Game game, Result result) {

        stageDao.insertObject(new Stage(game, playerMove, computerMove, result));
    }

    private Move getPlayerMove() {
        printOptions();
        return null;
    }

    private void printOptions() {
    }


    private Move getComputerMove() {
        return null;
    }


    private Result getResult() {
        return null;
    }
}
