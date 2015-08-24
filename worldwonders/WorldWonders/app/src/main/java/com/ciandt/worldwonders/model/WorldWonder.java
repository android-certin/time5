package com.ciandt.worldwonders.model;

/**
 * Created by pmachado on 8/23/15.
 */
public class WorldWonder {
    /*
    * Neste modulo os nomes, descrições e imagens das maravilhas são carregados do database
    * e enviados para o sistema de renderização
    *
    * TODO: Conectar essa classe com um banco de dados para extrair dados sobre as maravilhas
    * */
    static String wonder[] = {
            "MARAVILHA 1",
            "MARAVILHA 2",
            "MARAVILHA 3"
    };
    static String wonderImg[] = {
            "url://MARAVILHA 1_url",
            "url://MARAVILHA 2_url",
            "url://MARAVILHA 3_url"
    };
    static String wonderDesc[] = {
            "desc: MARAVILHA 1",
            "desc: MARAVILHA 2",
            "desc: MARAVILHA 3"
    };


    public static int getWondersCount() { return wonder.length; }

    public static String getWonderName(int id) { return wonder[id]; }

    public static String getWonderImage(int id) { return wonderImg[id]; }

    public static String getWonderDescription(int id) { return wonderDesc[id]; }
}
