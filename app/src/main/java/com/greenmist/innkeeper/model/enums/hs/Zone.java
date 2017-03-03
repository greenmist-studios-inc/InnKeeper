package com.greenmist.innkeeper.model.enums.hs;

/**
 * Created by geoff.powell on 2/3/17.
 */
public enum Zone {
    HAND,
    PLAY,
    DECK,
    GRAVEYARD,
    SETASIDE,
    UNKNOWN;

    public static Zone parse(String string) {
        for (Zone zone : values()) {
            if (zone.name().equalsIgnoreCase(string)) {
                return zone;
            }
        }
        return UNKNOWN;
    }
}
