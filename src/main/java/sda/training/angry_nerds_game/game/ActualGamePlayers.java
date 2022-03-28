package sda.training.angry_nerds_game.game;

import sda.training.angry_nerds_game.schema.AngryNerdsPlayer;

import java.util.LinkedList;
import java.util.Queue;

public class ActualGamePlayers {

    private Queue<AngryNerdsPlayer> players = new LinkedList<>();

    public Queue<AngryNerdsPlayer> getPlayers() {
        return players;
    }

    //TODO ZROBIC wrzucanie graczy z pozycji menuu

    public void initPlayers() {

        AngryNerdsPlayer player1 = new AngryNerdsPlayer();
        player1.setName("Marcin");
        players.add(player1);

        AngryNerdsPlayer player2 = new AngryNerdsPlayer();
        player2.setName("Karol");
        players.add(player2);

    }

}
