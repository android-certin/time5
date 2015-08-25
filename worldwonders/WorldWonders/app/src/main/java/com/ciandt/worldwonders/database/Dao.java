package com.ciandt.worldwonders.database;

import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */
public interface Dao<T> {
    List<T> getAll();

    T getById(int id);

    List<T> search(String word);

    boolean delete(T args);

    boolean update(T values,
                          int id);

    boolean insert(T values);

    void close();
}
