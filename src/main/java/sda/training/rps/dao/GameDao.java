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
    public int countGames() {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            return (int) session.createQuery("FROM Game g", Game.class).getResultStream().count();
        }
    }

    @Override
    public List<Game> findAll(int maxResults, int firstResult) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Game> games = session.createQuery("FROM Game g", Game.class).setMaxResults(maxResults).setFirstResult(firstResult).getResultList();

            return Objects.requireNonNullElse(games, Collections.emptyList());
        }
    }

    @Override
    public List<Game> findAllOfPlayer(Player player) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Game> games = session.createQuery("SELECT g FROM Game g " +
                    "JOIN g.player p " +
                    "WHERE p.id = :pId", Game.class)
                    .setParameter("pId", player.getId())
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
                    "and g.result = null", Game.class).setParameter("pId", player.getId()).getResultList();

            return Objects.requireNonNullElse(games, Collections.emptyList());
        }
    }
}
