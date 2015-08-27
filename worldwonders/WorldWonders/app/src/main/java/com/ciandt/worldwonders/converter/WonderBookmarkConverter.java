package com.ciandt.worldwonders.converter;

import com.ciandt.worldwonders.model.WonderBookmark;

import java.util.HashMap;

/**
 * Created by pmachado on 8/26/15.
 */
public class WonderBookmarkConverter extends ConverterBase<WonderBookmark> {

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
