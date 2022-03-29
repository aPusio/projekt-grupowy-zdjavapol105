package sda.training.angry_nerds_game.dao;

import java.util.List;

public interface GenericDao<T> {

    T findById(int id) throws RuntimeException;
    void insertObject(T t);
    void deleteObject(T t);
    void deleteObject(int id);
}
