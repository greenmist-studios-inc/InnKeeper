package com.greenmist.innkeeper.android.manager;

import android.content.Context;
import android.os.Environment;

import com.greenmist.innkeeper.android.log.HSLogMessage;
import com.greenmist.innkeeper.android.utils.DateUtils;
import com.greenmist.innkeeper.android.utils.IKLogger;
import com.greenmist.innkeeper.android.utils.StringUtils;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

import static com.greenmist.innkeeper.android.utils.Constants.HS_PACKAGE;
import static com.greenmist.innkeeper.android.utils.FileUtils.copyFile;

/**
 * Created by geoff.powell on 2/3/17.
 */
public class HSLogManager {

    private static final Pattern LOG_REGEX_PATTERN = Pattern.compile("\\w (\\d+:\\d+:\\d+.\\d+) ([\\w.]+\\(\\)) -\\s+(.*)");

    private static File hsFilesExternalDir = new File(Environment.getExternalStorageDirectory(), "/Android/data/" + HS_PACKAGE + "/files/");
    private static DateUtils dateUtils = new DateUtils();

    public static void createLogConfig(Context context, IKLogger logger) {
        if (!hsFilesExternalDir.exists()) {
            hsFilesExternalDir.mkdirs();
        }

        File hsLogConfig = new File(hsFilesExternalDir, "log.config");
        try {
            copyFile(context.getAssets().open("log.config"), hsLogConfig);
        } catch (IOException e) {
            logger.e(e, "Error reading or creating log.config");
        }
    }

    public static Observable<HSLogMessage> startLogScanService(String filename, IKLogger logger) {
        return Observable.create((ObservableOnSubscribe<HSLogMessage>) emitter -> {
            File file = new File(hsFilesExternalDir, "/Logs/" + filename);

            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

                try {
                    String line = bufferedReader.readLine();
                    do {
                        line = bufferedReader.readLine();
                        if (StringUtils.isNotBlank(line)) {
                            HSLogMessage hsLogMessage = parseLogMessage(line);

                            if (hsLogMessage != null) {
                                emitter.onNext(hsLogMessage);
                            }
                        }
                    }
                    while (StringUtils.isNotBlank(line) && !emitter.isDisposed());
                } catch (Exception e) {
                    Logger.e(e, "Error reading log files");
                    emitter.onError(e);
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException ignored) {
                    }
                }
            } else {
                logger.d("Cant find log file: " + filename);
            }

            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
                .share();
    }

    private static HSLogMessage parseLogMessage(String message) {
        Matcher matcher = LOG_REGEX_PATTERN.matcher(message);

        if (matcher.find()) {
            String timeStamp = matcher.group(1);
            String loggingMethod = matcher.group(2);
            String logMessage = matcher.group(3).trim();

            if (StringUtils.isNotBlank(timeStamp) && StringUtils.isNotBlank(loggingMethod) && StringUtils.isNotBlank(logMessage)) {
                HSLogMessage hsLogMessage = new HSLogMessage();
                hsLogMessage.setTimeStamp(dateUtils.parseTime("HH:mm:ss.SSSSSSS", timeStamp));
                hsLogMessage.setLoggingMethod(loggingMethod);
                hsLogMessage.setMessage(logMessage);

                return hsLogMessage;
            }
        }

        return null;
    }
}
