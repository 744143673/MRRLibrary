package com.hhg.mrrlibrary.retorfit.interceptor;

import com.hhg.mrrlibrary.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hhg on 2017/7/23.
 * description  : 日志拦截器
 * version      : 1.0.0
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.i("LoggingInterceptor : request.toString()" + request.toString());

        Response response = chain.proceed(request);
        LogUtils.i("LoggingInterceptor : response.toString()" + response.toString());

        return response;
    }
}
