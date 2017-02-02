package com.greenmist.innkeeper.model.enums;

/**
 * Created by geoff.powell on 2/2/17.
 */
public enum RequestCode {
    PERMISSION_READ_EXTERNAL (1);

    private int code;

    RequestCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
