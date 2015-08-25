package com.ciandt.worldwonders.converter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */
public interface ConverterBase<T> {
    List<T> fromListHashMap(List<HashMap<String, Object>> listTable);
    HashMap<String, Object> toHashMap(T v);
    T fromHashMap(HashMap<String, Object> table);
    int getId(T v);
}