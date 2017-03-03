package com.greenmist.innkeeper.android.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenmist.innkeeper.android.utils.DateUtils;
import com.greenmist.innkeeper.android.utils.StringUtils;

import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class PreferenceManaager {

    private static final String PREFERNCE_NAME = "innkeeper";

    private static PreferenceManaager instance;

    private SharedPreferences sharedPreferences;
    private Gson gson;

    private PreferenceManaager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERNCE_NAME, MODE_PRIVATE);
        this.gson = new GsonBuilder()
                    .setDateFormat(DateUtils.DEFAULT_DATE_FORMAT)
                    .create();
    }

    public static void init(Context context) {
        instance = new PreferenceManaager(context);
    }

    public static PreferenceManaager getInstance() {
        return instance;
    }

    public void put(String key, long value) {
        sharedPreferences.edit()
                .putLong(key, value)
                .apply();
    }

    public long get(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public void put(String key, int value) {
        sharedPreferences.edit()
                .putLong(key, value)
                .apply();
    }

    public int get(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void put(String key, float value) {
        sharedPreferences.edit()
                .putFloat(key, value)
                .apply();
    }

    public float get(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public void put(String key, String value) {
        sharedPreferences.edit()
                .putString(key, value)
                .apply();
    }

    public String get(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void put(String key, boolean value) {
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    public void put(String key, Serializable o) {
        String json = gson.toJson(o);
        put(key, json);
    }

    public <T extends Serializable> T get(String key, Class<T> typeCLass) {
        String json = sharedPreferences.getString(key, null);
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            return gson.fromJson(json, typeCLass);
        }
    }
}
