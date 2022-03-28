package sda.training.angry_nerds_game;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.training.HibernateFactory;
import sda.training.learning.Car;

public class AppTest {
    public static void main(String[] args) {


        HibernateFactory hibernateFactory = new HibernateFactory();
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();

        System.out.println("Testujemy ! powstawanie tabel w bazie");

        session.close();
        sessionFactory.close();

    }

}
