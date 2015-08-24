package com.ciandt.worldwonders.database;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 *
 * @v1 < @v2
 */
public interface Dao {
    public List<HashMap<String, Object>> getAll();

    public HashMap<String, Object> getById(int id);

    public List<HashMap<String, Object>> search(String word);


    public boolean delete(HashMap<String, Object> args);

    public boolean update(HashMap<String, Object> values,
                          HashMap<String, Object> where);

    public boolean insert(HashMap<String, Object> values);

    public void close();
}
