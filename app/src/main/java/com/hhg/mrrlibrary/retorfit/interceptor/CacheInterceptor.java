package com.hhg.mrrlibrary.retorfit.interceptor;

import com.hhg.mrrlibrary.MRRApplication;
import com.hhg.mrrlibrary.utils.LogUtils;
import com.hhg.mrrlibrary.utils.NetStateUtils;
import com.hhg.mrrlibrary.utils.ToastUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hhg on 2017/7/23.
 * description  : 缓存拦截器
 * version      : 1.0.0
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        boolean networkAvailable = NetStateUtils.isNetworkAvailable(MRRApplication.getInstance().getApplicationContext());
        LogUtils.i("isNetworkAvailable: " + networkAvailable);
        if (!networkAvailable) {
            //无网络  有缓存中的数据
            ToastUtils.showSecureShort("当前网络不可用，请检查您的网络设置！");
            LogUtils.i("CacheInterceptor 无网络");
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (networkAvailable) {
            //有网络
            LogUtils.i("有网络情况下  获取数据");
            //这里设置的为0就是说不进行缓存，我们也可以设置缓存
            // 时间
            String cacheControl = request.cacheControl().toString();
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 0)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Accept", "*/*")
                    .removeHeader("Pragma")
                    .build();
        } else {
            //无网络
            //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
            LogUtils.i("------无网络情况下  使用缓存");
            int maxTime = 4 * 24 * 60 * 60;
            return response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxTime)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
