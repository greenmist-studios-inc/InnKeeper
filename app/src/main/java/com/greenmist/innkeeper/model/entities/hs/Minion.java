package com.greenmist.innkeeper.model.entities.hs;

import com.greenmist.innkeeper.model.enums.hs.CardType;

/**
 * Created by geoff.powell on 2/3/17.
 */
public class Minion extends Card {

    private int damage;
    private int health;
    private int attack;
    private boolean silenced;
    private boolean windfury;
    private boolean taunt;
    private boolean stealth;
    private boolean charge;
    private boolean deathrattle;
    private boolean battlecry;
    private int jadeGolemCount;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean hasSilenced() {
        return silenced;
    }

    public void setSilenced(boolean silenced) {
        this.silenced = silenced;
    }

    public boolean hasWindfury() {
        return windfury;
    }

    public void setWindfury(boolean windfury) {
        this.windfury = windfury;
    }

    public boolean hasTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean hasStealth() {
        return stealth;
    }

    public void setStealth(boolean stealth) {
        this.stealth = stealth;
    }

    public boolean hasCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public boolean hasDeathrattle() {
        return deathrattle;
    }

    public void setDeathrattle(boolean deathrattle) {
        this.deathrattle = deathrattle;
    }

    public boolean hasBattlecry() {
        return battlecry;
    }

    public void setBattlecry(boolean battlecry) {
        this.battlecry = battlecry;
    }

    public int getJadeGolemCount() {
        return jadeGolemCount;
    }

    public void setJadeGolemCount(int jadeGolemCount) {
        this.jadeGolemCount = jadeGolemCount;
    }

    @Override
    public CardType getCardType() {
        return CardType.MINION;
    }
}
