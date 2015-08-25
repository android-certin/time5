package com.ciandt.worldwonders.database;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 *
 * @v1 < @v2
 */
public interface Dao<T> {
    public List<T> getAll();

    public T getById(int id);

    public List<T> search(String word);


    public boolean delete(T args);

    public boolean update(T values,
                          T where);

    public boolean insert(T values);

    public void close();
}
