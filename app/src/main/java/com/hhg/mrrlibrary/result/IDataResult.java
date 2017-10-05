package com.hhg.mrrlibrary.result;

import android.support.annotation.NonNull;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    : 任务处理结果回调方法
 * version : 1.0.0
 */

public interface IDataResult<T> {
    /**
     * 数据请求、处理 开始，对应到会调用 IViewResult 的 startViewResult()方法。
     */
    void startData();

    /**
     * 数据请求、处理 成功，对应到会调用 IViewResult 的 successViewResult(T t)方法。
     */
    void successData(T t);

    /**
     * 数据请求、处理 失败，对应到会调用 IViewResult 的 failViewResult(String t)方法。
     */
    void failData(@NonNull String msg);

    /**
     * 数据请求、处理 完成，对应到会调用 IViewResult 的 completeViewResult()方法。
     */
    void completeData();
}
