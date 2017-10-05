package com.hhg.mrrlibrary.comm;


import android.support.annotation.NonNull;

import com.hhg.mrrlibrary.widgets.CommDialogFragment;


/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    : 通用的view处理，Toast、 DialogFragment、Context、跳转
 * version : 1.0.0
 */

public interface ICommView {

    boolean isActive();

    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void hideLoading();

    /**
     * Toast
     *
     * @param msg msg
     */
    void showToastShort(@NonNull String msg);

    /**
     * 通用的提示DialogFragment
     */
    void showPointDialog(String strContent, boolean isShowCancel, CommDialogFragment.PosOnClickListener pos0nClickListener);
}
