package com.hhg.mrrlibrary.defaultimpl;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hhg.mrrlibrary.comm.ICommView;
import com.hhg.mrrlibrary.comm.IViewResult;
import com.hhg.mrrlibrary.result.EStatus;
import com.hhg.mrrlibrary.result.ResultBean;
import com.hhg.mrrlibrary.utils.LogUtils;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/8/24.
 * desc    : 默认的View处理类
 * version : 1.0.0
 */

public abstract class DefaultViewResultAbs<T> implements IViewResult<T> {

    private ICommView iCommView;

    protected DefaultViewResultAbs(@NonNull ICommView iCommView) {
        this.iCommView = iCommView;
    }

    @Override
    public void startViewResult() {
        LogUtils.i("startViewResult()--> ");
        if (iCommView.isActive())
            iCommView.showLoading();
    }

    @Override
    public void successViewResult(T success) {
        if (success == null) {
            LogUtils.e("结果为null ");
            failViewResult("数据格式错误，结果为null ");
            return;
        }
        LogUtils.i("successViewResult()--> " + success.toString());

        //统一的结果解析；
        try {
            if (success instanceof String) {
                ResultBean<String> resultBean = new Gson().fromJson(success.toString(), new TypeToken<ResultBean<String>>() {
                }.getType());
                //请求状态码判断
                for (EStatus eStatus : EStatus.values())
                    if (resultBean.getStatus().equals(eStatus.getValue()));
//                        iCommView.showToastShort("" + eStatus.getDec());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failViewResult(@NonNull String fail) {
        LogUtils.e("failViewResult()--> " + fail);
        if (iCommView.isActive()) {
            iCommView.hideLoading();
            iCommView.showPointDialog("" + fail, false, null);
        }
    }

    @Override
    public void completeViewResult() {
        if (iCommView.isActive())
            iCommView.hideLoading();
    }
}
