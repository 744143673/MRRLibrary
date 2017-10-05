package com.hhg.mrrlibrary.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IoUtils
 * Created by Alex on 2017/5/8.
 */

public class IoUtils {
    private static final String TAG = "IoUtils";

    /** 读满整个数组 */
    public static final void readBytes(InputStream in, byte[] buffer) throws IOException {
        readBytes(in, buffer, 0, buffer.length);
    }

    /** 读满指定长度的字节 */
    public static final void readBytes(InputStream in, byte[] buffer, int offset, int length)
            throws IOException {
        int sum = 0, readed;
        while (sum < length) {
            readed = in.read(buffer, offset + sum, length - sum);
            if (readed < 0) {
                throw new IOException("End of stream");
            }
            sum += readed;
        }
    }

    public static final void readToStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[256];
        int readed = 0;
        while ((readed = in.read(buffer)) != -1) {
            out.write(buffer, 0, readed);
        }
    }

    public static final void closeStream(OutputStream o) {
        if (o != null) {
            try {
                o.close();
            } catch (IOException e) {
                LogUtils.e( "error to close input stream");
            }
        }
    }

    public static final void closeStream(InputStream o) {
        if (o != null) {
            try {
                o.close();
            } catch (IOException e) {
                LogUtils.e( "error to close input stream");
            }
        }
    }
}

