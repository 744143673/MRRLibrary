package com.hhg.mrrlibrary.defaultimpl;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.hhg.mrrlibrary.MRRApplication;
import com.hhg.mrrlibrary.result.IDataResult;
import com.hhg.mrrlibrary.utils.LogUtils;
import com.hhg.mrrlibrary.utils.NetStateUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.DefaultSubscriber;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    :  实现了DefaultSubscriber、 主要是onError() 方法的默认处理
 * version : 1.0.0
 */

public abstract class DefaultSubscriberAbs<T> extends DefaultSubscriber<T> {

    private IDataResult<T> iResult;

    protected DefaultSubscriberAbs(@NonNull IDataResult<T> iResult) {
        this.iResult = iResult;
    }

    //在执行onNext前 会先执行onComplete()方法
    @Override
    public void onNext(T t) {
        LogUtils.i("onNext()--> " + t);
        iResult.successData(t);
    }

    @Override
    public void onError(Throwable t) {
        LogUtils.e("Throwable t --> " + t);
        String strError = "";
        if (t != null) {
            if (t instanceof SocketTimeoutException) {
                strError = "访问超时;  " + t;
            } else if (t instanceof UnknownHostException) {
                strError = "IP或端口不存在;  " + t;
            } else if (t instanceof ConnectException) {
                strError = "连接出错;  " + t;
                if (!NetStateUtils.isNetworkAvailable(MRRApplication.getInstance().getApplicationContext()))
                    strError = "当前网络不可用，请检查您的网络设置!";
            } else if (t instanceof HttpException) {
                strError = "504错误是（网关超时）;  " + t;
            } else {
                strError = "访问出错;  " + t;
            }
        }
        iResult.failData(strError);
    }

    @Override
    public void onComplete() {
        LogUtils.i("onComplete()");
        iResult.completeData();
    }
}
