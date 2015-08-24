package com.ciandt.worldwonders.model;

import java.io.Serializable;

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
