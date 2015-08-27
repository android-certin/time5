package com.ciandt.worldwonders.converter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */
public abstract class ConverterBase<T> {
    public ConverterBase() {
    }

    public List<T> fromListHashMap(List<HashMap<String, Object>> listTable) {
        List<T> l = new ArrayList<>();
        for (HashMap<String, Object> t : listTable) {
            l.add(fromHashMap(t));
        }
        return l;
    }

    public abstract HashMap<String, Object> toHashMap(T v);

    public abstract T fromHashMap(HashMap<String, Object> table);

    public abstract int getId(T v);
}
