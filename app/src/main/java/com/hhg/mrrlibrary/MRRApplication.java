package com.hhg.mrrlibrary;

import android.app.Application;

import com.hhg.mrrlibrary.tools.ActivityManager;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/10/5.
 * desc    :
 * version : 1.0.0
 */

public  class MRRApplication extends Application {
    private static MRRApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ActivityManager.getInstance().registerSelf(this);
    }

    public static MRRApplication getInstance() {
        if (instance == null)
            instance = new MRRApplication();
        return instance;
    }
}
