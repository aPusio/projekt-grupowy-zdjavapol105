package sda.training.angry_nerds_game.dao;

import org.hibernate.Session;
import sda.training.HibernateFactory;


public class GenericDaoImpl<T> implements GenericDao<T> {


    private Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T findById(int id) throws RuntimeException {
        Session session = openSession();
        T result =  session.find(entityClass, id);
        session.close();
        return result;
    }

    @Override
    public void insertObject(T t) {
        Session session = openSession();
        session.beginTransaction();
        session.persist(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteObject(T t) {
        Session session = openSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteObject(int id) {
        T t = findById(id);
        if (t != null) {
            deleteObject(t);
        }
    }

    private Session openSession() {
        return new HibernateFactory().getSessionFactory().openSession();
    }
}
