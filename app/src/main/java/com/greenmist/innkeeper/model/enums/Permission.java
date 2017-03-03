package com.greenmist.innkeeper.model.enums;

import android.Manifest;

/**
 * Created by geoff.powell on 2/2/17.
 */
public enum  Permission {

    READ_EXTERNAL_STORAGE (RequestCode.PERMISSION_READ_EXTERNAL.getCode(), Manifest.permission.READ_EXTERNAL_STORAGE),
    WRITE_EXTERNAL_STORAGE (RequestCode.PERMISSION_WRITE_EXTERNAL.getCode(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

    private int requestCode;
    private String permission;

    Permission(int requestCode, String permission) {
        this.requestCode = requestCode;
        this.permission = permission;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public String getPermission() {
        return permission;
    }
}
