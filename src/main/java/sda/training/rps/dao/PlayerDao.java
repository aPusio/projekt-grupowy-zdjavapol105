package sda.training.rps.dao;

import org.hibernate.Session;
import sda.training.HibernateFactory;
import sda.training.rps.model.Player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlayerDao implements IPlayerDao {
    @Override
    public Player findById(int id) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            return session.find(Player.class, id);
        }
    }

    @Override
    public Player findByName(String name) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
             Optional<Player> optional =  session.createQuery("FROM Player p WHERE name = :n", Player.class)
                    .setParameter("n", name)
                    .uniqueResultOptional();
            return optional.orElse(null);
        }
    }

    @Override
    public Player mergeObject(Player player) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            session.beginTransaction();
            player = (Player) session.merge(player);
            session.getTransaction().commit();
            return player;
        }
    }

    @Override
    public List<Player> findBest(int topNumber) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Player> players = session
                    .createQuery("FROM Player p ORDER BY p.score DESC", Player.class)
                    .setMaxResults(topNumber)
                    .getResultList();
            return Objects.requireNonNullElse(players, Collections.emptyList());
        }
    }

    @Override
    public List<Player> findAll() {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Player> players = session
                    .createQuery("FROM Player p ORDER BY p.score DESC", Player.class)
                    .getResultList();
            return Objects.requireNonNullElse(players, Collections.emptyList());
        }
    }
}
