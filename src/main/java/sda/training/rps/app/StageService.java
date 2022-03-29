package sda.training.rps.app;

import sda.training.rps.dao.StageDao;
import sda.training.rps.model.Game;
import sda.training.rps.model.Stage;
import sda.training.rps.util.Move;
import sda.training.rps.util.Result;
import sda.training.rps.util.ScannerSingleton;

import java.util.*;
import java.util.stream.Collectors;

public class StageService {
    private final StageDao stageDao = new StageDao();
    private Move playerMove;
    private Move computerMove;
    public final Result[][] outcome = getOutcome();
    private final ScannerSingleton scanner = ScannerSingleton.getInstance();
    private final PlayerService playerService;

    public StageService(PlayerService playerService) {
        this.playerService = playerService;
    }

    private Result[][] getOutcome() {
        return new Result[][]{
                {Result.DRAW, Result.LOSE, Result.LOSE, Result.LOSE, Result.WIN, Result.WIN, Result.WIN},
                {Result.WIN, Result.DRAW, Result.LOSE, Result.LOSE, Result.LOSE, Result.WIN, Result.WIN},
                {Result.WIN, Result.WIN, Result.DRAW, Result.LOSE, Result.LOSE, Result.LOSE, Result.WIN},
                {Result.WIN, Result.WIN, Result.WIN, Result.DRAW, Result.LOSE, Result.LOSE, Result.LOSE},
                {Result.LOSE, Result.WIN, Result.WIN, Result.WIN, Result.DRAW, Result.LOSE, Result.LOSE},
                {Result.LOSE, Result.LOSE, Result.WIN, Result.WIN, Result.WIN, Result.DRAW, Result.LOSE},
                {Result.LOSE, Result.LOSE, Result.LOSE, Result.WIN, Result.WIN, Result.WIN, Result.DRAW}
        };
    }

    public Result playStage(Game game) {
        playerMove = getPlayerMove();
        computerMove = getComputerMove(game);
        Result result = getResult(game);
        saveStage(game, result);
        return result;
    }

    private Move getPlayerMove() {
        Move move;
        do {
            printOptions();
            move = Move.getMoveById(scanner.scannerInt());
        } while (move.equals(Move.OTHER));
        return move;
    }

    private void printOptions() {
        System.out.println("0. Kamień");
        System.out.println("1. Woda");
        System.out.println("2. Powietrze");
        System.out.println("3. Papier");
        System.out.println("4. Gąbka");
        System.out.println("5. Nożyczki");
        System.out.println("6. Ogień");
    }

    public Move getComputerMove(Game game) {
        Random random = new Random();
        List<Stage> stages = stageDao.findAllByPlayer(game.getPlayer());

        if (stages.size() < 10) {
            return Move.getMoveById(random.nextInt(7));
        } else {
            List<Move> playerCommonMoves = getPlayerMostMoves(stages);

            return getFinalComputerMove(random, playerCommonMoves);
        }
    }

    private Move getFinalComputerMove(Random random, List<Move> playerCommonMoves) {
        List<Move> firstCounterMoves = getCounter(playerCommonMoves.get(0));
        List<Move> secondCounterMoves;

        if (playerCommonMoves.size() > 1) {
            secondCounterMoves = getCounter(playerCommonMoves.get(1));
        } else {
            secondCounterMoves = getCounter(Move.OTHER);
        }
        List<Move> counterMoves = new ArrayList<>(firstCounterMoves);

        counterMoves.retainAll(secondCounterMoves);
        if (counterMoves.size() == 0) {
            return firstCounterMoves.get(random.nextInt(3));
        } else {
            return counterMoves.get(random.nextInt(counterMoves.size()));
        }
    }

    private List<Move> getPlayerMostMoves(List<Stage> stages) {
        List<Move> moves = stages.stream().map(Stage::getPlayerMove).collect(Collectors.toList());
        Map<Move, Integer> playerMoves = new HashMap<>();
        for (Move playerMove : moves) {
            Integer numberOfMoves = playerMoves.get(playerMove);
            playerMoves.put(playerMove, numberOfMoves == null ? 1 : numberOfMoves + 1);
        }
        return playerMoves
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private List<Move> getCounter(Move move) {
        switch (move) {
            case ROCK:
                return Arrays.asList(Move.WATER, Move.AIR, Move.PAPER);
            case WATER:
                return Arrays.asList(Move.AIR, Move.PAPER, Move.SPONGE);
            case AIR:
                return Arrays.asList(Move.PAPER, Move.SPONGE, Move.SCISSORS);
            case PAPER:
                return Arrays.asList(Move.SPONGE, Move.SCISSORS, Move.FIRE);
            case SPONGE:
                return Arrays.asList(Move.ROCK, Move.SCISSORS, Move.FIRE);
            case SCISSORS:
                return Arrays.asList(Move.ROCK, Move.WATER, Move.FIRE);
            case FIRE:
                return Arrays.asList(Move.ROCK, Move.WATER, Move.AIR);
            default:
                return Arrays.asList(Move.values());
        }
    }

    private Result getResult(Game game) {
        System.out.println("Twój ruch: " + playerMove.getMoveString());
        System.out.println("Ruch przeciwnika: " + computerMove.getMoveString());
        Result result = outcome[playerMove.getMoveInt()][computerMove.getMoveInt()];
        System.out.println("Wynik: " + result);
        if (result.equals(Result.WIN)) {
            game.getPlayer().setScore(game.getPlayer().getScore() + 1);
            playerService.updatePlayer(game.getPlayer());

        }

        return result;
    }

    private void saveStage(Game game, Result result) {
        stageDao.insertObject(new Stage(game, playerMove, computerMove, result));
    }
}
