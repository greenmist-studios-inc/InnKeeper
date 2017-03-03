package com.greenmist.innkeeper.android.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "MM-dd-yyyy'T'hh:mm:ssSSSZ";

    private DateTimeFormatter formatter;

    private static DateTimeParser[] dateTimeParser = new DateTimeParser[] {
            DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT).getParser()
    };

    public DateUtils() {
        formatter = new DateTimeFormatterBuilder()
                .append(null, dateTimeParser)
                .toFormatter();
    }

    public DateTime parse(String dateTimeString) {
        return formatter.parseDateTime(dateTimeString);
    }

    public DateTime parse(String format, String dateTimeString) {
        return DateTimeFormat.forPattern(format).parseDateTime(dateTimeString);
    }

    public LocalTime parseTime(String format, String dateTimeString) {
        return LocalTime.parse(dateTimeString, DateTimeFormat.forPattern(format));
    }

    public String convert(String format, DateTime dateTime) {
        return DateTimeFormat.forPattern(format).print(dateTime);
    }
}
