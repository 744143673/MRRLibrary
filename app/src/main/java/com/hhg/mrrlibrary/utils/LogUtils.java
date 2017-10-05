package com.hhg.mrrlibrary.utils;

import android.util.Log;

/**
 * Created by hhg on 2017/7/23.
 * description  :
 * version      :1.0.0
 */

public class LogUtils {

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    private static final String TAG = "hhg";
    private static final String MARK = "\t";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, MARK + msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, MARK + msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, MARK + msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, MARK + msg);
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w(TAG, MARK + msg);
    }


    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, MARK + msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, MARK + msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, MARK + msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, MARK + msg);
    }

}