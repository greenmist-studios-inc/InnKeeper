package com.greenmist.innkeeper.android.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by geoff.powell on 2/5/17.
 */
public class FileUtils {

    private static IKLogger logger = new IKLogger();

    public static boolean copyFile(InputStream inputStream, File to) {
        boolean result = true;

        FileOutputStream outputStream = null;
        try {
            if (!to.exists() && !to.createNewFile()) {
                return false;
            }
            outputStream = new FileOutputStream(to);

            byte[] buffer = new byte[1024];
            int n;
            while (-1 != (n = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, n);
            }
        } catch (IOException e) {
            logger.e(e, "Error reading or creating log.config");
            result = false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
        }

        return result;
    }
}
