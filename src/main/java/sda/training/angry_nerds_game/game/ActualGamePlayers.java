package sda.training.angry_nerds_game.game;

import sda.training.angry_nerds_game.dao.GenericDaoImpl;
import sda.training.angry_nerds_game.schema.AngryNerdsDBPlayer;

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

        String playerName= getPlayerName();

        AngryNerdsDBPlayer angryNerdsDBPlayer = new AngryNerdsDBPlayer();


        angryNerdsDBPlayer.setName(playerName);

        // zapisac w bazie gracza i pobrać jego id z bazy
        // do lokalnego gracza przekazac Id konstruktorem
        GenericDaoImpl genericDao = new GenericDaoImpl(AngryNerdsDBPlayer.class);
        genericDao.insertObject(angryNerdsDBPlayer);


        players.add(new AngryNerdsPlayer(angryNerdsDBPlayer.getId(), playerName));

    }

}
