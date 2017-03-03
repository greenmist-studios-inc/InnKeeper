package com.greenmist.logger;

import com.greenmist.logger.formatter.LoggerFormatter;
import com.greenmist.logger.handlers.LoggerHandler;
import com.greenmist.logger.model.LoggerMessage;
import com.greenmist.logger.model.enums.LogLevel;
import com.greenmist.logger.model.enums.MessageType;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class Logger {

    private static Logger instance;

    private LogLevel logLevel;
    private LoggerHandler loggerHandler;
    private LoggerFormatter loggerFormatter;

    private Logger() {}

    public static Settings init() {
        return new Settings();
    }

    public static Logger getInstance() {
        return instance;
    }

    public void v(String message) {
        if (logLevel.ordinal() <= LogLevel.FULL.ordinal()) {
            loggerHandler.log(loggerFormatter.format(new LoggerMessage(MessageType.VERBOSE, message)));
        }
    }

    public void d(String message) {
        if (logLevel.ordinal() >= LogLevel.BASIC.ordinal()) {
            loggerHandler.log(loggerFormatter.format(new LoggerMessage(MessageType.DEBUG, message)));
        }
    }

    public void a(String message) {
        if (logLevel.ordinal() >= LogLevel.BASIC.ordinal()) {
            loggerHandler.log(loggerFormatter.format(new LoggerMessage(MessageType.ASSERT, message)));
        }
    }

    public void e(String message) {
        if (logLevel.ordinal() >= LogLevel.BASIC.ordinal()) {
            loggerHandler.log(loggerFormatter.format(new LoggerMessage(MessageType.ERROR, message)));
        }
    }

    public void w(String message) {
        if (logLevel.ordinal() <= LogLevel.FULL.ordinal()) {
            loggerHandler.log(loggerFormatter.format(new LoggerMessage(MessageType.WARN, message)));
        }
    }

    public void i(String message) {
        if (logLevel.ordinal() <= LogLevel.FULL.ordinal()) {
            loggerHandler.log(loggerFormatter.format(new LoggerMessage(MessageType.INFO, message)));
        }
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LoggerHandler getLoggerHandler() {
        return loggerHandler;
    }

    public void setLoggerHandler(LoggerHandler loggerHandler) {
        this.loggerHandler = loggerHandler;
    }

    public LoggerFormatter getLoggerFormatter() {
        return loggerFormatter;
    }

    public void setLoggerFormatter(LoggerFormatter loggerFormatter) {
        this.loggerFormatter = loggerFormatter;
    }

    public static class Settings {

        private Settings() {
            instance = new Logger();
        }

        public Settings logLevel(LogLevel logLevel) {
            instance.setLogLevel(logLevel);
            return this;
        }

        public Settings loggerHandler(LoggerHandler loggerHandler) {
            instance.setLoggerHandler(loggerHandler);
            return this;
        }

        public Settings loggerFormatter(LoggerFormatter loggerFormatter) {
            instance.setLoggerFormatter(loggerFormatter);
            return this;
        }
    }
}
