package com.ciandt.worldwonders.converter;

import com.ciandt.worldwonders.model.Wonder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 */public class WonderConverter implements ConverterBase<Wonder> {

    @Override
    public List<Wonder> fromListHashMap(List<HashMap<String, Object>> listTable) {
        List<Wonder> l = new ArrayList<Wonder>();
        for (HashMap<String, Object> t: listTable) {
            l.add(fromHashMap(t));
        }
        return l;
    }

    @Override
    public HashMap<String, Object> toHashMap(Wonder v) {
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("name", v.name);
        m.put("id", v.id);
        m.put("description", v.description);
        m.put("url", v.url);
        m.put("photo", v.photo);
        m.put("latitude", v.latitude);
        m.put("longitude", v.longitude);
        return m;
    }

    @Override
    public Wonder fromHashMap(HashMap<String, Object> table) {
        return new Wonder(
                table.get("name").toString(),
                (int) table.get("id"),
                table.get("description").toString(),
                table.get("url").toString(),
                table.get("photo").toString(),
                (double) table.get("longitude"),
                (double) table.get("latitude"));
    }

    @Override
    public int getId(Wonder v) {
        return v.id;
    }
}
