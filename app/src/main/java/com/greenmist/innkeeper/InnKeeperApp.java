package com.greenmist.innkeeper;

import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.greenmist.innkeeper.android.BuildConfig;
import com.greenmist.innkeeper.android.manager.EndpointManager;
import com.greenmist.innkeeper.android.manager.PreferenceManaager;
import com.greenmist.innkeeper.android.dao.IKRealmMigration;
import com.greenmist.logger.formatter.BasicLoggerFormatter;
import com.greenmist.logger.handlers.FileLoggerHandler;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by geoff.powell on 1/20/17.
 */
public class InnKeeperApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        if (BuildConfig.DEBUG) {
            enabledStrictMode();
            LeakCanary.install(this);
        }

        PreferenceManaager.init(this);

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

            com.greenmist.logger.Logger.init()
                    .loggerFormatter(new BasicLoggerFormatter())
                    .loggerHandler(new FileLoggerHandler(this, "output"))
                    .logLevel(com.greenmist.logger.model.enums.LogLevel.FULL);
        } else {
            Logger.init()
                    .logLevel(LogLevel.NONE)
                    .methodCount(0);

            com.greenmist.logger.Logger.init()
                    .loggerFormatter(new BasicLoggerFormatter())
                    .loggerHandler(new FileLoggerHandler(this, "output"))
                    .logLevel(com.greenmist.logger.model.enums.LogLevel.BASIC);
        }

        EndpointManager.init(this);
    }

    private void enabledStrictMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }
}
