package com.greenmist.innkeeper.model.enums;

import android.app.Activity;
import android.support.annotation.IdRes;

import com.greenmist.innkeeper.android.R;
import com.greenmist.innkeeper.android.activities.AboutActivity;
import com.greenmist.innkeeper.android.activities.DecksActivity;
import com.greenmist.innkeeper.android.activities.ReplaySelectionActivity;
import com.greenmist.innkeeper.android.activities.SettingsActivity;
import com.greenmist.innkeeper.android.activities.StatsActivity;

/**
 * Created by geoff.powell on 2/2/17.
 */
public enum  NavDrawerItem {

    DECKS (R.id.decks, DecksActivity.class),
    STATS (R.id.stats, StatsActivity.class),
    REPLAYS (R.id.replays, ReplaySelectionActivity.class),
    SETTINGS (R.id.settings, SettingsActivity.class),
    ABOUT (R.id.about, AboutActivity.class);

    private int menuId;
    private Class<? extends Activity> activityClass;

    NavDrawerItem(int menuId, Class<? extends Activity> activityClass) {
        this.menuId = menuId;
        this.activityClass = activityClass;
    }

    public Class<? extends Activity> getActivityClass() {
        return activityClass;
    }

    public static NavDrawerItem getNavDrawerItem(@IdRes int menuId) {
        for (NavDrawerItem navDrawerItem : values()) {
            if (navDrawerItem.menuId == menuId) {
                return navDrawerItem;
            }
        }

        return null;
    }
}
