package sda.training.angry_nerds_game.game;

import sda.training.angry_nerds_game.schema.AngryNerdsPlayer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ActualGamePlayers {

    private Queue<AngryNerdsPlayer> players = new LinkedList<>();

    public Queue<AngryNerdsPlayer> getPlayers() {
        return players;
    }

    public String getPlayerName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj swoje imię : ");
        return scanner.nextLine();
    }

    public void addAngryNerdsPlayer(){

        players.add(new AngryNerdsPlayer(getPlayerName()));

    }

}
