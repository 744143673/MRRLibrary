package com.hhg.mrrlibrary.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import com.yinshang.hsroad.HSRoadApplication;


/**
 * Created by hhg on 2017/7/23.
 * description  : 包装Toast
 * version      : 1.0.0
 */

public class ToastUtils {
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static Toast toast;

    public static void showShort(String string) {
        getToast(string, Toast.LENGTH_SHORT).show();
        //showCenterToast(string);
    }

    //线程安全
    public static void showSecureShort(final String string) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                getToast(string, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showLong(String string) {
        getToast(string, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ShowToast")
    private static Toast getToast(String string, int duration) {
        if (toast == null) {
            if (string == null)
                string = "";
            toast = Toast.makeText(HSRoadApplication.getInstance().getApplicationContext(), string, duration);
        } else {
            toast.setText(string);
        }
        return toast;
    }

    /**
     * 居中的Toast
     *
     * @param message
     */
    public static void showCenterToast(String message) {
        Toast toast = getToast(message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}