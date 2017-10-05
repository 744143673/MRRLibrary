package com.hhg.mrrlibrary.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * File处理
 * Created by Alex on 2017/5/8.
 */

public class FileUtils {
    private static final String TAG = "FileUtils";

    public static final String readFileToString(String path) {
        return readFileToString(path, "UTF-8");
    }

    public static final String readFileToString(String path, String charset) {
        File file = new File(path);
        if (!file.exists()) {
            loge("readFileToString：文件不存在 " + path);
            return null;
        }
        if (file.isDirectory()) {
            loge("readFileToString：文件路径是一个目录 " + path);
            return null;
        }

        int length = (int) file.length();
        byte[] buffer = new byte[length];
        try {
            InputStream in = new FileInputStream(file);
            IoUtils.readBytes(in, buffer);
            return new String(buffer, charset);
        } catch (Exception e) {
            loge("readFileToString：读取失败 " + path, e);
        }
        return null;
    }

    public static final String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static final void loge(String msg, Throwable e) {
        if (msg == null) {
            msg = "";
        }
    }

    protected static final void loge(final String msg) {
        if (msg != null) {
        }
    }
}
