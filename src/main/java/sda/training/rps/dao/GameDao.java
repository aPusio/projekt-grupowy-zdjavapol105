package sda.training.rps.dao;

import org.hibernate.Session;
import sda.training.HibernateFactory;
import sda.training.rps.model.Game;
import sda.training.rps.model.Player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameDao implements IGameDao {
    @Override
    public Game findById(int id) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            return session.find(Game.class, id);
        }
    }

    @Override
    public Game mergeObject(Game game) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            session.beginTransaction();
            game = (Game) session.merge(game);
            session.getTransaction().commit();
            return game;
        }
    }

    @Override
    public int countFinishedGames() {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            return session.createQuery("SELECT g FROM Game g " +
                    "WHERE g.result IS NOT null", Game.class).getResultList().size();
        }
    }

    @Override
    public int countFinishedGamesOfPlayer(Player player) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            return session.createQuery("SELECT g FROM Game g " +
                            "JOIN g.player p " +
                            "WHERE p.name = :playerName " +
                            "and g.result IS NOT null", Game.class)
                    .setParameter("playerName", player.getName())
                    .getResultList().size();
        }
    }


    @Override
    public List<Game> findAllFinished(int maxResults, int firstResult) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Game> games = session.createQuery("SELECT g FROM Game g " +
                            "WHERE g.result IS NOT null", Game.class)
                    .setMaxResults(maxResults)
                    .setFirstResult(firstResult)
                    .getResultList();

            return Objects.requireNonNullElse(games, Collections.emptyList());
        }
    }

    @Override
    public List<Game> findAllFinishedOfPlayer(int maxResults, int firstResult, Player player) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Game> games = session.createQuery("SELECT g FROM Game g " +
                            "JOIN g.player p " +
                            "WHERE p.id = :pId " +
                            "and g.result IS NOT null", Game.class)
                    .setParameter("pId", player.getId())
                    .setMaxResults(maxResults)
                    .setFirstResult(firstResult)
                    .getResultList();

            return Objects.requireNonNullElse(games, Collections.emptyList());
        }
    }

    @Override
    public List<Game> findAllNotCompleteOfPlayer(Player player) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Game> games = session.createQuery("SELECT g FROM Game g " +
                            "JOIN g.player p " +
                            "WHERE p.id = :pId " +
                            "and g.result = null", Game.class).setParameter("pId", player.getId()).
                    getResultList();

            return Objects.requireNonNullElse(games, Collections.emptyList());
        }
    }
}
