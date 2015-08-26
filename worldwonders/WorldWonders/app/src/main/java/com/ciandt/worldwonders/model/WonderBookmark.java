package com.ciandt.worldwonders.model;

import com.ciandt.worldwonders.converter.WonderBookmarkConverter;

import java.io.Serializable;

/**
 * Created by pmachado on 8/26/15.
 */
public class WonderBookmark implements Serializable {
    public int id;
    public int idWonder;

    public static WonderBookmarkConverter getConverter() {
        return new WonderBookmarkConverter();
    }

    public WonderBookmark(int id, int idWonder) {
        this.id = id;
        this.idWonder = idWonder;
    }

    public WonderBookmark(int idWonder) {
        this.idWonder = idWonder;
    }

    public WonderBookmark() {
        this.id = 0;
        this.idWonder = 0;
    }
}
