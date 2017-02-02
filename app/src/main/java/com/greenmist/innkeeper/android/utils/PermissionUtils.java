package com.greenmist.innkeeper.android.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.greenmist.innkeeper.model.enums.Permission;

/**
 * Created by geoff.powell on 2/2/17.
 */
public class PermissionUtils {

    public int checkPermission(Activity activity, Permission permission) {
        int permissionResult = ContextCompat.checkSelfPermission(activity, permission.getPermission());
        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission.getPermission())) {
                return PackageManager.GET_PERMISSIONS;
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{ permission.getPermission() },
                        permission.getRequestCode());
            }
        }

        return permissionResult;
    }
}
