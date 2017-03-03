package com.greenmist.innkeeper.android.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class IKLogger {

    private com.greenmist.logger.Logger logger;

    public IKLogger() {
        this.logger = com.greenmist.logger.Logger.getInstance();
    }

    public void d(Object object) {
        logger.d(object.toString());
        Logger.d(object);
    }

    public void d(String message, Object... args) {
        logger.d(message);
        Logger.d(message, args);
    }

    public void w(String message) {
        logger.w(message);
        Logger.w(message);
    }

    public void w(String message, Object... args) {
        logger.w(message);
        Logger.w(message, args);
    }

    public void e(String message) {
        logger.e(message);
        Logger.e(message);
    }

    public void e(Throwable throwable, String message) {
        logger.e(message);
        Logger.e(throwable, message);
    }

    public void v(String message) {
        logger.v(message);
        Logger.v(message);
    }

    public void v(String message, Object... args) {
        logger.v(message);
        Logger.v(message, args);
    }

    public void i(String message) {
        logger.i(message);
        Logger.i(message);
    }

    public void i(String message, Object... args) {
        logger.i(message);
        Logger.i(message, args);
    }
}
