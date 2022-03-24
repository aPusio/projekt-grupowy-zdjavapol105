package sda.training.rps.dao;

import org.hibernate.Session;
import sda.training.HibernateFactory;
import sda.training.rps.model.Game;
import sda.training.rps.model.Player;
import sda.training.rps.model.Stage;
import sda.training.rps.util.Result;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StageDao implements IStageDao {
    @Override
    public Stage findById(int id) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            return session.find(Stage.class, id);
        }
    }

    @Override
    public void insertObject(Stage stage) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(stage);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Stage> findAllByGame(Game game) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Stage> stages = session.createQuery("SELECT s FROM Stage s " +
                            "JOIN s.game g " +
                            "WHERE g.id = :gId", Stage.class)
                    .setParameter("gId", game.getId())
                    .getResultList();

            return Objects.requireNonNullElse(stages, Collections.emptyList());
        }
    }

    @Override
    public List<Stage> findAllByGameWinByPlayer(Game game) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Stage> stages = session.createQuery("SELECT s FROM Stage s " +
                            "JOIN s.game g " +
                            "WHERE g.id = :gId " +
                            "and s.result = :res", Stage.class)
                    .setParameter("gId", game.getId())
                    .setParameter("res", Result.WIN)
                    .getResultList();

            return Objects.requireNonNullElse(stages, Collections.emptyList());
        }
    }

    @Override
    public List<Stage> findAllByGameWinByComputer(Game game) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Stage> stages = session.createQuery("SELECT s FROM Stage s " +
                            "JOIN s.game g " +
                            "WHERE g.id = :gId " +
                            "and s.result = :res", Stage.class)
                    .setParameter("gId", game.getId())
                    .setParameter("res", Result.LOSE)
                    .getResultList();

            return Objects.requireNonNullElse(stages, Collections.emptyList());
        }
    }

    @Override
    public List<Stage> findAllByPlayer(Player player) {
        try (Session session = new HibernateFactory().getSessionFactory().openSession()) {
            List<Stage> stages = session.createQuery("SELECT s FROM Stage s " +
                            "JOIN s.game g " +
                            "JOIN g.player p  " +
                            "WHERE p.id = :pId", Stage.class)
                    .setParameter("pId", player.getId())
                    .getResultList();

            return Objects.requireNonNullElse(stages, Collections.emptyList());
        }
    }
}
