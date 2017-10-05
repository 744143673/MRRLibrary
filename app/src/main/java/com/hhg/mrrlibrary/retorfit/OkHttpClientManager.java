package com.hhg.mrrlibrary.retorfit;

import com.hhg.mrrlibrary.MRRApplication;
import com.hhg.mrrlibrary.comm.Constant;
import com.hhg.mrrlibrary.retorfit.interceptor.CacheInterceptor;
import com.hhg.mrrlibrary.retorfit.interceptor.LoggingInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by hhg on 2017/7/23.
 * description  : 设置OkHttpClient配置
 * version      : 1.0.0
 */

public class OkHttpClientManager extends OkHttpClient {

    public static OkHttpClient getOkHttpClient() {
        //设置缓存路径
        File cacheDir = MRRApplication.getInstance().getApplicationContext().getCacheDir();
        File cacheFile = new File(cacheDir, "cacheData");
        //设置缓存大小
        Cache cache = new Cache(cacheFile, 1024L);
        return new Builder()
                .retryOnConnectionFailure(true)//连接失败后是否重新连接
                .connectTimeout(Constant.HttpConstant.CONNECT_TIME_OUT, TimeUnit.SECONDS)//超时时间
                .readTimeout(Constant.HttpConstant.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.HttpConstant.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
//                .addNetworkInterceptor(new LoggingInterceptor())
//                .addNetworkInterceptor(new CacheInterceptor())//添加的是网络拦截器，他会在在request和resposne是分别被调用一次，
                .addInterceptor(new CacheInterceptor())//添加的是aplication拦截器，他只会在response被调用一次
                .cache(cache)
                .build();
    }
}
