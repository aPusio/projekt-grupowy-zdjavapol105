package sda.training;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.training.learning.Car;
import sda.training.learning.EngineType;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        HibernateFactory hibernateFactory = new HibernateFactory();
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();

        Car car = new Car();
        car.setName("maluch diesel");
        car.setEngineType(EngineType.DIESEL);
        session.save(car);

        session.close();
        sessionFactory.close();
    }
}
