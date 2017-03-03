package com.greenmist.innkeeper.android.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.greenmist.innkeeper.android.utils.IKLogger;
import com.greenmist.innkeeper.android.utils.PermissionUtils;
import com.greenmist.innkeeper.mvvm.MVVMBase;

import io.realm.Realm;

/**
 * Created by geoff.powell on 1/20/2017.
 */

public abstract class GMBaseActivity extends AppCompatActivity implements MVVMBase.View {

    protected IKLogger logger;
    protected Realm realm;
    protected PermissionUtils permissionUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        permissionUtils = new PermissionUtils();
        realm = Realm.getDefaultInstance();
        logger = new IKLogger();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getViewModel().onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getViewModel().onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getViewModel().onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getViewModel().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getViewModel().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    @Override
    public void onBackPressed() {
        if (getViewModel().onBackPressed()) {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @NonNull
    public abstract MVVMBase.ViewModel getViewModel();

    @Override
    public Context getContext() {
        return this;
    }


}
