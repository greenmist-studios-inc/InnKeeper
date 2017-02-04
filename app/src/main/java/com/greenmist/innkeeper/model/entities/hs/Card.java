package com.greenmist.innkeeper.model.entities.hs;

import com.greenmist.innkeeper.model.enums.hs.CardFlair;
import com.greenmist.innkeeper.model.enums.hs.CardType;
import com.greenmist.innkeeper.model.enums.hs.Rarity;
import com.greenmist.innkeeper.model.enums.hs.Zone;

/**
 * Created by geoff.powell on 2/3/17.
 */
public abstract class Card {

    private int id;
    private String cardId;
    private String name;
    private Zone zone;
    private int zonePosition;
    private int cost;
    private int lastCostInHand;
    private CardFlair cardFlair;
    private Rarity rarity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public int getZonePosition() {
        return zonePosition;
    }

    public void setZonePosition(int zonePosition) {
        this.zonePosition = zonePosition;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLastCostInHand() {
        return lastCostInHand;
    }

    public void setLastCostInHand(int lastCostInHand) {
        this.lastCostInHand = lastCostInHand;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public CardFlair getCardFlair() {
        return cardFlair;
    }

    public void setCardFlair(CardFlair cardFlair) {
        this.cardFlair = cardFlair;
    }

    public abstract CardType getCardType();
}
