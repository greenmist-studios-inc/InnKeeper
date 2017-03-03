package com.greenmist.innkeeper.android.utils;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class StringUtils {

    public static boolean isBlank(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }
}
