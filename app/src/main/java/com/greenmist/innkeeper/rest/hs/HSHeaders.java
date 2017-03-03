package com.greenmist.innkeeper.rest.hs;

/**
 * Created by geoff.powell on 2/7/17.
 */
public enum HSHeaders {

    MASHAPE_KEY("X-Mashape-Key");

    private String key;

    HSHeaders(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
