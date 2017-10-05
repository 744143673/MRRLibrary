package com.hhg.mrrlibrary.defaultimpl;

import android.support.annotation.NonNull;

import com.hhg.mrrlibrary.comm.IViewResult;
import com.hhg.mrrlibrary.result.IDataResult;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    : 提供默认的结果处理
 * version : 1.0.0
 */

public abstract class DefaultDataResultAbs<T> implements IDataResult<T> {

    private IViewResult<T> iViewResult;

    protected DefaultDataResultAbs(@NonNull IViewResult<T> iViewResult) {
        this.iViewResult = iViewResult;
    }

    @Override
    public void startData() {
        iViewResult.startViewResult();
    }

    @Override
    public void failData(@NonNull String msg) {
        iViewResult.failViewResult(msg);
    }

    @Override
    public void completeData() {
        iViewResult.completeViewResult();
    }
}
