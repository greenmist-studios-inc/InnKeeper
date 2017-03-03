package com.greenmist.logger.formatter;

import com.greenmist.logger.model.LoggerMessage;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class BasicLoggerFormatter implements LoggerFormatter {

    private static final String TIME_FORMAT = "MM-dd hh:mm:ss.SSS";

    private DateTimeFormatter dateTimeFormatter;

    public BasicLoggerFormatter() {
        this(TIME_FORMAT);
    }

    public BasicLoggerFormatter(String format) {
        this.dateTimeFormatter = DateTimeFormat.forPattern(format);
    }

    @Override
    public String format(LoggerMessage loggerMessage) {
        return dateTimeFormatter.print(loggerMessage.getTimestamp()) +
                ' ' +
                loggerMessage.getThread() +
                "/" +
                loggerMessage.getMessageType() +
                ": " +
                loggerMessage.getMessage();
    }
}
