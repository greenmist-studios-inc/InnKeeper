package com.greenmist.innkeeper.model.entities.hs;

import com.greenmist.innkeeper.model.enums.hs.CardType;

/**
 * Created by geoff.powell on 2/3/17.
 */
public class Weapon extends Card {

    private int attack;
    private int durability;

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public CardType getCardType() {
        return CardType.WEAPON;
    }
}
