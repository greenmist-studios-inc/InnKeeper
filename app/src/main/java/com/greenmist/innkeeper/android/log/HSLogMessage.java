package com.greenmist.innkeeper.android.log;

import org.joda.time.LocalTime;

/**
 * Created by geoff.powell on 2/5/17.
 */
public class HSLogMessage {

    private LocalTime timeStamp;
    private String loggingMethod;
    private String message;

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLoggingMethod() {
        return loggingMethod;
    }

    public void setLoggingMethod(String loggingMethod) {
        this.loggingMethod = loggingMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
