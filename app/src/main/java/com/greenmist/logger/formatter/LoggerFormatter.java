package com.greenmist.logger.formatter;

import com.greenmist.logger.model.LoggerMessage;

/**
 * Created by geoff.powell on 2/4/17.
 */
public interface LoggerFormatter {

    String format(LoggerMessage loggerMessage);
}
