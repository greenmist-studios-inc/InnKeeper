package com.greenmist.logger.handlers;

import android.content.Context;
import android.os.Environment;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by geoff.powell on 2/4/17.
 */
public class FileLoggerHandler implements LoggerHandler {

    private static final String DATETIME_FORMAT = "M-d-yyyy_h-m-ss";
    private File logFile;
    private PrintWriter printWriter;

    public FileLoggerHandler(Context context, String name) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATETIME_FORMAT);

        logFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + File.separator + formatter.print(DateTime.now()) + "_" + name + ".log");
    }

    @Override
    public void log(String string) {
        checkFileOpenOrExists();

        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
            printWriter.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    private void checkFileOpenOrExists() {
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
