package com.greenmist.logger.model;

import com.greenmist.logger.model.enums.MessageType;

import org.joda.time.DateTime;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class LoggerMessage {

    private MessageType messageType;
    private DateTime timestamp;
    private String message;
    private String thread;

    public LoggerMessage(MessageType messageType, String message) {
        this.messageType = messageType;
        this.message = message;
        this.timestamp = DateTime.now();
        this.thread = Thread.currentThread().getName();
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }
}
