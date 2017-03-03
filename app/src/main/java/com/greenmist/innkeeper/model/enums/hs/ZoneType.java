package com.greenmist.innkeeper.model.enums.hs;

/**
 * Created by geoff.powell on 2/19/17.
 */
public enum ZoneType {
    FRIENDLY,
    OPPOSING,
    UNKNOWN;

    public static ZoneType parse(String string) {
        for (ZoneType zoneType : values()) {
            if (zoneType.name().equalsIgnoreCase(string)) {
                return zoneType;
            }
        }
        return UNKNOWN;
    }
}
