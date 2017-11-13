package com.hhg.mrrlibrary.comm;

import android.support.annotation.NonNull;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/8/24.
 * desc    : 得到请求、处理的数据后 更新View的回调接口
 * version : 1.0.0
 */

public interface IViewResult<T> {

    /**
     * 一般用于加载框的显示。
     */
    void startViewResult();

    /**
     * 数据 请求、处理成功时回调；
     * 此处不用隐藏加载框，因为会先回调completeViewResult() 方法， （在执行onNext前 会先执行onComplete()方法）。
     *
     * @param success 泛型数据
     */
    void successViewResult(T success);


    /**
     * 解析数据后 data 不为null 是调用
     *
     * @param success 泛型数据
     */
    void withDataBean(T success);

    /**
     * 数据 请求、处理失败 + 隐藏框架框。
     *
     * @param fail 处理失败的msg
     */
    void failViewResult(@NonNull String fail);

    /**
     * 一般用于隐藏框架框
     */
    void completeViewResult();
}
