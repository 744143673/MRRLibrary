package com.hhg.mrrlibrary.retorfit.interceptor;

import com.hhg.mrrlibrary.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/11/13.
 * desc    :
 * version : 1.0.0
 */

public class HeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        LogUtils.i("HeadersInterceptor");
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        setRequest(builder).build();
        Response response = chain.proceed(request);
        getHeaderResponse(response);
        return response;
    }

    /**
     * 设置自己定义的header
     */
    protected Request.Builder setRequest(Request.Builder builder) {
        return builder
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Cookie", "add cookies here");
    }

    /**
     * 通过Response获取header
     */
    protected Response getHeaderResponse(Response response) {
        return response;
    }
}
