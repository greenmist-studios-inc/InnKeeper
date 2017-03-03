package com.greenmist.innkeeper.model.entities.hs;

import com.greenmist.innkeeper.model.enums.hs.CardType;

import java.io.Serializable;

/**
 * Created by geoff.powell on 2/3/17.
 */
public class Spell extends Card implements Serializable {
    @Override
    public CardType getCardType() {
        return CardType.SPELL;
    }
}
