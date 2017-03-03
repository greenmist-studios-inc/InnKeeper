package com.greenmist.innkeeper.android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayMap;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by geoff.powell on 2/18/17.
 */
public class HashMapDataBindingAdapter {

    @BindingAdapter("bind:propertyMap")
    public static void setPropertyMap(TextView textView, ObservableArrayMap<String, String> stringHashMap) {
        textView.setText("");
        for (Map.Entry<String, String> entry : stringHashMap.entrySet()) {
            textView.append(entry.getKey());
            textView.append(": ");
            textView.append(entry.getValue());
            textView.append("\n");
        }
    }
}
