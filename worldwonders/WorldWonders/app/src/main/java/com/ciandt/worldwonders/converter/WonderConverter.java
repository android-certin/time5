package com.ciandt.worldwonders.converter;

import com.ciandt.worldwonders.model.Wonder;

import java.util.HashMap;

/**
 * Created by pmachado on 8/24/15.
 */
public class WonderConverter extends ConverterBase<Wonder> {
    @Override
    public HashMap<String, Object> toHashMap(Wonder v) {
        HashMap<String, Object> m = new HashMap<>();
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
