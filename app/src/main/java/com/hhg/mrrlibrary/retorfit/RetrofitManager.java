package com.hhg.mrrlibrary.retorfit;

import com.hhg.mrrlibrary.comm.Constant;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hhg on 2017/7/23.
 * description  : Retrofit 配置
 * version      : 1.0.0
 */

public class RetrofitManager {
    public static Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Constant.HttpConstant.base_url)
                .addConverterFactory(ScalarsConverterFactory.create())//增加返回值为String的支持
                .addConverterFactory(GsonConverterFactory.create())//增加返回值为Gson的支持(以实体类返回)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//增加返回值为Oservable<T>的支持
                .client(client) //设置okhttp
//                .client(OkHttpClientManager.getOkHttpClient(context))
                .build();
    }
}
