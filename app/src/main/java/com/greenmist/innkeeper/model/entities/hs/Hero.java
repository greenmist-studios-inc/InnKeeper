package com.greenmist.innkeeper.model.entities.hs;

import java.io.Serializable;

/**
 * Created by geoff.powell on 2/3/17.
 */
public class Hero implements Serializable {

    private int heroId;
    private String name;

    public Hero(int heroId, String name) {
        this.heroId = heroId;
        this.name = name;
    }
}
