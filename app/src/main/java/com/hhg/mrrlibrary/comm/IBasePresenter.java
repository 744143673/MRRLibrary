package com.hhg.mrrlibrary.comm;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    : MVP -- 定义Presenter 基类 通用的方法
 * version : 1.0.0
 */

public interface IBasePresenter<V> {
//    void attachView(V v);

//    V getView();

//    boolean isViewAttached();

//    void detachView();

    /**
     * 一些初始化操作
     */
    void start();
}
