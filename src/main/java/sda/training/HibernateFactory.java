package sda.training;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import sda.training.angry_nerds_game.schema.AngryNerdsDBPlayer;
import sda.training.angry_nerds_game.schema.AngryNerdsDBShot;
import sda.training.learning.Car;

public class HibernateFactory {
    private Configuration getHibernateConfig() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://sql4.freemysqlhosting.net:3306/sql4480996");
        configuration.setProperty("hibernate.connection.username", "sql4480996");
        configuration.setProperty("hibernate.connection.password", "rESVqFX1lI");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addAnnotatedClass(AngryNerdsDBPlayer.class);
        configuration.addAnnotatedClass(AngryNerdsDBShot.class);

       return configuration;
    }
    public SessionFactory getSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(getHibernateConfig().getProperties()).build();
        return getHibernateConfig().buildSessionFactory(registry);
    }
}
