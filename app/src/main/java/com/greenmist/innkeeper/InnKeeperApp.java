package com.greenmist.innkeeper;

import android.support.multidex.MultiDexApplication;

import com.greenmist.innkeeper.android.BuildConfig;
import com.greenmist.innkeeper.dao.IKRealmMigration;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by geoff.powell on 1/20/17.
 */
public class InnKeeperApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .migration(new IKRealmMigration())
                .schemaVersion(IKRealmMigration.CURRENT_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        if (BuildConfig.DEBUG) {
            Logger.init()
                    .logLevel(LogLevel.FULL)
                    .methodCount(4);
        } else {
            Logger.init()
                    .logLevel(LogLevel.NONE)
                    .methodCount(0);
        }
    }
}
