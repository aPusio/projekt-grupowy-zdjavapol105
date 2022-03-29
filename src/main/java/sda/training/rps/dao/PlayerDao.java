package sda.training.rps.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.training.HibernateFactory;
import sda.training.rps.model.Player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlayerDao {

    public Player findById(int id) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            Player player = session.find(Player.class, id);
            session.close();
            factory.close();
            return player;
        }
    }


    public Optional<Player> findByName(String name) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            Optional<Player> optional = session.createQuery("FROM Player p WHERE name = :n", Player.class)
                    .setParameter("n", name)
                    .uniqueResultOptional();
            session.close();
            factory.close();
            return optional;
        }
    }


    public Player mergeObject(Player player) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            player = (Player) session.merge(player);
            session.getTransaction().commit();
            session.close();
            factory.close();
            return player;
        }
    }


    public List<Player> findBest(int topNumber) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            List<Player> players = session
                    .createQuery("FROM Player p ORDER BY p.score DESC", Player.class)
                    .setMaxResults(topNumber)
                    .getResultList();
            session.close();
            factory.close();
            return Objects.requireNonNullElse(players, Collections.emptyList());
        }
    }

}
