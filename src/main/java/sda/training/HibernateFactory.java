package sda.training;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import sda.training.learning.Car;
import sda.training.rps.model.Game;
import sda.training.rps.model.Player;
import sda.training.rps.model.Stage;

public class HibernateFactory {
    private Configuration getHibernateConfig() {
        Configuration configuration = new Configuration();
       // configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/projekt");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://sql4.freemysqlhosting.net:3306/sql4480996");
       // configuration.setProperty("hibernate.connection.username", "root");
          configuration.setProperty("hibernate.connection.username", "sql4480996");
       // configuration.setProperty("hibernate.connection.password", "malina123");
            configuration.setProperty("hibernate.connection.password", "rESVqFX1lI");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Stage.class);

        return configuration;
    }

    public SessionFactory getSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(getHibernateConfig().getProperties()).build();
        return getHibernateConfig().buildSessionFactory(registry);
    }
}
