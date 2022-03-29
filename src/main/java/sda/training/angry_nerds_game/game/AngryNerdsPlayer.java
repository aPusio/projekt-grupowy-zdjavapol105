package sda.training.angry_nerds_game.game;

public class AngryNerdsPlayer {

    private String name;
    private int life = 5;
    private int score = 0;

    public AngryNerdsPlayer(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
}

