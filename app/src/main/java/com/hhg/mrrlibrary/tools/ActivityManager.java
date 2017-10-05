package com.hhg.mrrlibrary.tools;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.hhg.mrrlibrary.utils.LogUtils;

import java.util.Stack;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/8/24.
 * desc    : 应用程序Activity管理类：用于Activity管理和应用程序退出； ActivityLifecycleCallbacks
 * version : 1.0.0
 */
public class ActivityManager implements Application.ActivityLifecycleCallbacks {
    private String TAG = "ActivityLifecycleCallbacks";

    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    /**
     * 单一实例
     */
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void registerSelf(Context context) {
        LogUtils.i(TAG, "registerSelf");
        Application application = (Application) context.getApplicationContext();
        application.registerActivityLifecycleCallbacks(ActivityManager.getInstance());
    }

    public void registerSelf(Application application) {
        LogUtils.i(TAG, "registerSelf");
        application.registerActivityLifecycleCallbacks(ActivityManager.getInstance());
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        //回调方法分别都先于Activity的生命周期方法
        LogUtils.i(TAG, "onActivityCreated  " + activity.getClass().getName());
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtils.i(TAG, "onActivityStarted " + activity.getClass().getName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtils.i(TAG, "onActivityResumed " + activity.getClass().getName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtils.i(TAG, "onActivityPaused " + activity.getClass().getName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtils.i(TAG,"onActivityStopped " + activity.getClass().getName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        LogUtils.i(TAG, "onActivitySaveInstanceState " + activity.getClass().getName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.i(TAG, "onActivityDestroyed " + activity.getClass().getName());
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    public void finishAllActivity() {
        if (activityStack == null) {
            return;
        }
        for (Activity activity : activityStack) {
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } catch (Exception ignored) {
        }
    }
}
