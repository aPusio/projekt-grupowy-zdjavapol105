package sda.training.angry_nerds_game.game;

public class SingletonGameConfig {

        private static final SingletonGameConfig instance = new SingletonGameConfig();

        public SingletonGameConfig() {}

        public static SingletonGameConfig getInstance(){
            return instance;
        }

    private int MaxX = 181;
    private int MaxY = 31;

    boolean color = true;

    private int[][] gameBoard = new int[MaxX][MaxY];


    public int getMaxX() {
        return MaxX;
    }

    public int getMaxY() {
        return MaxY;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }
}
