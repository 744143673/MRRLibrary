package com.hhg.mrrlibrary.comm;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    : MVP -- View基类
 * version : 1.0.0
 */

public interface IBaseView<T> {

    /**
     * 绑定Presenter
     */
    void setPresenter(T presenter);
}
