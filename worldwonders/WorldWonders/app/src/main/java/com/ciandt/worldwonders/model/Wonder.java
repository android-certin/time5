package com.ciandt.worldwonders.model;

import com.ciandt.worldwonders.converter.WonderConverter;

import java.io.Serializable;

/**
 * Created by pmachado on 8/23/15.
 */
public class Wonder implements Serializable {
    public int id;
    public String name;
    public String description;
    public String url;
    public String photo;
    public double latitude;
    public double longitude;
    public boolean isMarked;

    public Wonder(String name,
                  int id,
                  String description,
                  String url,
                  String photo,
                  double longitude,
                  double latitude) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.url = url;
        this.photo = photo;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isMarked = false;
    }

    public Wonder(String name, int id) {
        this.name = name;
        this.id = id;
        this.isMarked = false;
    }

    public static WonderConverter getConverter() {
        return new WonderConverter();
    }
}
