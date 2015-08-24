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

    static Wonder fromHashMap(HashMap<String, Object> table) {
        Wonder w = new Wonder(table.get("name"), table.get("id"), table.get("description"), 
        table.get("url"), table.get("photo"), table.get("longitude"), table.get("latitude"));
        return w;
    }
    
    static List<Wonder> fromListHashMap(List<HashMap<String, Object>> listTable) {
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
