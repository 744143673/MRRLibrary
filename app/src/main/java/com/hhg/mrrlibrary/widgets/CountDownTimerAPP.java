package com.hhg.mrrlibrary.widgets;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.hhg.mrrlibrary.utils.LogUtils;


import java.lang.ref.WeakReference;

/**
 * Created by hhg on 2017/7/14.
 * description  : 倒计时控件
 * version      : 1.0.0
 */

public class CountDownTimerAPP extends CountDownTimer {

    private WeakReference<TextView> weakReference;
    private String strLoading = "加载中…";
    private long countDownInterval;

    public static CountDownTimerAPP instance(WeakReference<TextView> weakReference, long millisInFuture, long countDownInterval) {
        return new CountDownTimerAPP(weakReference, millisInFuture, countDownInterval);
    }

    private CountDownTimerAPP(WeakReference<TextView> weakReference, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.countDownInterval = countDownInterval;
        this.weakReference = weakReference;
    }

    @Override
    public void onTick(long lTime) {
        if (weakReference != null && weakReference.get() != null) {
            LogUtils.i("lTime" + (lTime / countDownInterval));
            weakReference.get().setText(strLoading + "  (" + lTime / countDownInterval + "s )");
        }
    }

    @Override
    public void onFinish() {
        if (weakReference != null && weakReference.get() != null)
            weakReference.get().setText(strLoading);
    }
}
