package com.greenmist.innkeeper.android.manager;

import android.content.Context;

import com.greenmist.innkeeper.rest.hs.HSWebClient;

/**
 * Created by geoff.powell on 2/5/17.
 */
public class EndpointManager {

    public static void init(Context context) {
        HSWebClient.init(context);
    }
}
