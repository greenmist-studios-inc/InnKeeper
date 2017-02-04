package com.greenmist.innkeeper.model.entities.hs;

import java.util.HashMap;

/**
 * Created by geoff.powell on 1/21/17.
 */
public class Player {

    private int id;
    private String name;
    private int turn;
    private boolean combo;
    private Hero hero;
    private HashMap<String, Card> cardMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean hasCombo() {
        return combo;
    }

    public void setCombo(boolean combo) {
        this.combo = combo;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public HashMap<String, Card> getCardMap() {
        return cardMap;
    }

    public void setCardMap(HashMap<String, Card> cardMap) {
        this.cardMap = cardMap;
    }

}
