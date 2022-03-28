package sda.training.rps.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.training.HibernateFactory;
import sda.training.rps.model.Game;
import sda.training.rps.model.Player;
import sda.training.rps.model.Stage;
import sda.training.rps.util.Result;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StageDao {

    public Stage findById(int id) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            return session.find(Stage.class, id);
        }
    }


    public void insertObject(Stage stage) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(stage);
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Stage> findAllByGameWinByPlayer(Game game) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            List<Stage> stages = session.createQuery("SELECT s FROM Stage s " +
                            "JOIN s.game g " +
                            "WHERE g.id = :gId " +
                            "and s.result = :res", Stage.class)
                    .setParameter("gId", game.getId())
                    .setParameter("res", Result.WIN)
                    .getResultList();
            session.close();
            factory.close();
            return Objects.requireNonNullElse(stages, Collections.emptyList());
        }
    }


    public List<Stage> findAllByGameWinByComputer(Game game) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            List<Stage> stages = session.createQuery("SELECT s FROM Stage s " +
                            "JOIN s.game g " +
                            "WHERE g.id = :gId " +
                            "and s.result = :res", Stage.class)
                    .setParameter("gId", game.getId())
                    .setParameter("res", Result.LOSE)
                    .getResultList();
            session.close();
            factory.close();
            return Objects.requireNonNullElse(stages, Collections.emptyList());
        }
    }


    public List<Stage> findAllByPlayer(Player player) {
        try (SessionFactory factory = new HibernateFactory().getSessionFactory();
             Session session = factory.openSession()) {
            List<Stage> stages = session.createQuery("SELECT s FROM Stage s " +
                            "JOIN s.game g " +
                            "JOIN g.player p  " +
                            "WHERE p.id = :pId", Stage.class)
                    .setParameter("pId", player.getId())
                    .getResultList();
            session.close();
            factory.close();
            return Objects.requireNonNullElse(stages, Collections.emptyList());
        }
    }
}
