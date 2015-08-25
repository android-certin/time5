package com.ciandt.worldwonders.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pmachado on 8/23/15.
 */
public class Wonder implements Serializable {
    /*
    * Neste modulo os nomes, descrições e imagens das maravilhas são carregados do database
    * e enviados para o sistema de renderização
    *
    * TODO: Conectar essa classe com um banco de dados para extrair dados sobre as maravilhas
    * */

    public int id;
    public String name;
    public String description;
    public String url;
    public String photo;
    public double latitude;
    public double longitude;

    public static Wonder fromHashMap(HashMap<String, Object> table) {
        return new Wonder(
                table.get("name").toString(),
                (int) table.get("id"),
                table.get("description").toString(),
                table.get("url").toString(),
                table.get("photo").toString(),
                (int) table.get("longitude"),
                (int) table.get("latitude"));
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("name", name);
        m.put("id", id);
        m.put("description", description);
        m.put("url", url);
        m.put("photo", photo);
        m.put("latitude", latitude);
        m.put("longitude", longitude);
        return m;
    }
    
    public static List<Wonder> fromListHashMap(List<HashMap<String, Object>> listTable) {
        List<Wonder> l = new ArrayList<Wonder>();
        for (HashMap<String, Object> t: listTable) {
            l.add(fromHashMap(t));
        }
        return l;
    }
    
    public Wonder(String name, int id, String description, String url, String photo, double longitude, double latitude) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.url = url;
        this.photo = photo;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Wonder(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
