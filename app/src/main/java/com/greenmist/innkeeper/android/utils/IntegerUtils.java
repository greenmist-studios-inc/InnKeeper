package com.greenmist.innkeeper.android.utils;

/**
 * Created by geoff.powell on 2/20/17.
 */
public class IntegerUtils {

    public static boolean isInteger(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
