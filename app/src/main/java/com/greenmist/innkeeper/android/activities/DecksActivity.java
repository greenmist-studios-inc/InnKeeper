package com.greenmist.innkeeper.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.greenmist.innkeeper.android.R;
import com.greenmist.innkeeper.mvvm.MVVMBase;

/**
 * Created by geoff.powell on 1/20/2017.
 */

public class DecksActivity extends GMBaseDrawerActivity {

    @Override
    protected void setupDependencies() {
        setToolbarTitle(R.string.title_decks);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_decks;
    }

    //TODO Fix
    @NonNull
    @Override
    public MVVMBase.ViewModel getViewModel() {
        return new MVVMBase.ViewModel() {
            @Override
            public void onCreate(Bundle savedInstanceState) {

            }

            @Override
            public void onResume() {

            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {

            }

            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

            }

            @Override
            public void onPause() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public boolean onBackPressed() {
                return true;
            }
        };
    }
}
