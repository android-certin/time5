package com.ciandt.worldwonders.converter;

import com.ciandt.worldwonders.model.WonderBookmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmachado on 8/26/15.
 */
public class WonderBookmarkConverter implements ConverterBase<WonderBookmark> {
    @Override
    public List<WonderBookmark> fromListHashMap(List<HashMap<String, Object>> listTable) {
        List<WonderBookmark> list = new ArrayList<WonderBookmark>();
        for (HashMap<String, Object> t: listTable) {
            list.add(fromHashMap(t));
        }
        return list;
    }

    @Override
    public HashMap<String, Object> toHashMap(WonderBookmark v) {
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("id", v.id);
        m.put("idWonders", v.idWonder);
        return m;
    }

    @Override
    public WonderBookmark fromHashMap(HashMap<String, Object> table) {
        return new WonderBookmark((int) table.get("id"),
                (int) table.get("idWonders"));
    }

    @Override
    public int getId(WonderBookmark v) {
        return v.id;
    }
}
