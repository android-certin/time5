package com.ciandt.worldwonders.model;

import java.io.Serializable;

/**
 * Created by daianefs on 21/08/15.
 */
public class User implements Serializable {
    public String name;
    public String user;

    public User(String name, String user) {
        this.name = name;
        this.user = user;
    }
}
