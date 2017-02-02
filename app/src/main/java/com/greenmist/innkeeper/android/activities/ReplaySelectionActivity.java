package com.greenmist.innkeeper.android.activities;

import com.greenmist.innkeeper.android.R;

/**
 * Created by geoff.powell on 1/20/2017.
 */

public class ReplaySelectionActivity extends GMBaseDrawerActivity {

    @Override
    protected void setupDependencies() {
        setToolbarTitle(R.string.title_replays);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_replay_selection;
    }
}
