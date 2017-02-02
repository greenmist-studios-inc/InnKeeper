package com.greenmist.innkeeper.dao;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by geoff.powell on 1/21/17.
 */
public class IKRealmMigration implements RealmMigration {

    public static final int VERSION_ONE = 1;
    public static final int CURRENT_VERSION = VERSION_ONE;

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }
}
