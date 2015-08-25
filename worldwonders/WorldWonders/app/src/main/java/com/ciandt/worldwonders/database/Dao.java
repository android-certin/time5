package com.ciandt.worldwonders.database;

import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */
public interface Dao<T> {
    List<T> getAll();

    T getById(int id);

    List<T> search(String sql);

    boolean delete(T value);

    boolean update(T value);

    boolean insert(T value);

    void close();
}
