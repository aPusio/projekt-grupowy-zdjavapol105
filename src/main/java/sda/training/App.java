package sda.training;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sda.training.learning.Car;

import java.util.Scanner;

public class App
{
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(App.class);

    public static void main(String[] args )
    {
        System.out.println( "Hello World!" );

        HibernateFactory hibernateFactory = new HibernateFactory();
        SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();

        log.error("HELLO FROM LOGGER! {}", "ARGUMENT");

        Scanner scanner = new Scanner(System.in);
        System.out.println("WYBIERZ GRE: ");
        System.out.println("1. Tu wstaw gre 🚀🚀🚀");
        System.out.println("2. Tu wstaw gre 🚀🚀🚀");
        System.out.println("3. Tu wstaw gre 🚀🚀🚀");
        System.out.println("4. Tu wstaw gre 🚀🚀🚀");
        int gameNumber = scanner.nextInt();

        switch (gameNumber){
            case 1:
                System.out.println("Zaimplementuj mnie 🤣");
                break;
            case 2:
                System.out.println("Zaimplementuj mnie 🤣");
                break;
            case 3:
                System.out.println("Zaimplementuj mnie 🤣");
                break;
            case 4:
                System.out.println("Zaimplementuj mnie 🤣");
                break;
            default:
                System.out.println("Bardzo sie starałeś, lecz gry nie wybrałeś");
        }


        session.close();
        sessionFactory.close();
    }

    private static Car storingCarInDb(Session session) {
        Car car = new Car();
        car.setName("maluch");
        session.save(car);
        return car;
    }
}
