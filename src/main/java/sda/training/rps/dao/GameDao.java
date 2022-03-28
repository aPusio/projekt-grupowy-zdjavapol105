package sda.training.rps.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.training.HibernateFactory;
import sda.training.rps.model.Game;
import sda.training.rps.model.Player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class GameDao {

    public Game findById(int id) {

        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            Game game = session.find(Game.class, id);
            session.close();
            factory.close();
            return game;
        }
    }

    public Game mergeObject(Game game) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            game = (Game) session.merge(game);
            session.getTransaction().commit();
            session.close();
            factory.close();
            return game;
        }
    }


    public int countFinishedGames() {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            int count = session.createQuery("SELECT g FROM Game g " +
                    "WHERE g.result IS NOT null", Game.class).getResultList().size();
            session.close();
            factory.close();
            return count;
        }
    }


    public int countFinishedGamesOfPlayer(Player player) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            int count = session.createQuery("SELECT g FROM Game g " +
                            "JOIN g.player p " +
                            "WHERE p.name = :playerName " +
                            "and g.result IS NOT null", Game.class)
                    .setParameter("playerName", player.getName())
                    .getResultList().size();
            session.close();
            factory.close();
            return count;
        }
    }


    public List<Game> findAllFinished(int maxResults, int firstResult) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            List<Game> games = session.createQuery("SELECT g FROM Game g " +
                            "WHERE g.result IS NOT null", Game.class)
                    .setMaxResults(maxResults)
                    .setFirstResult(firstResult)
                    .getResultList();
            session.close();
            factory.close();
            return Objects.requireNonNullElse(games, Collections.emptyList());
        }
    }

    public List<Game> findAllFinishedOfPlayer(int maxResults, int firstResult, Player player) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            List<Game> games = session.createQuery("SELECT g FROM Game g " +
                            "JOIN g.player p " +
                            "WHERE p.id = :pId " +
                            "and g.result IS NOT null", Game.class)
                    .setParameter("pId", player.getId())
                    .setMaxResults(maxResults)
                    .setFirstResult(firstResult)
                    .getResultList();
            session.close();
            factory.close();
            return Objects.requireNonNullElse(games, Collections.emptyList());
        }
    }

    public List<Game> findAllNotCompleteOfPlayer(Player player) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            List<Game> games = session.createQuery("SELECT g FROM Game g " +
                            "JOIN g.player p " +
                            "WHERE p.id = :pId " +
                            "and g.result = null", Game.class).setParameter("pId", player.getId()).
                    getResultList();
            session.close();
            factory.close();
            return Objects.requireNonNullElse(games, Collections.emptyList());
        }
    }


}
