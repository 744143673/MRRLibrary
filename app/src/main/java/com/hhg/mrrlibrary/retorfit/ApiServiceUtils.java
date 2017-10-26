package com.hhg.mrrlibrary.retorfit;


import android.os.Build;
import android.support.annotation.RequiresApi;

import com.hhg.mrrlibrary.comm.ApiService;

/**
 * Created by hhg on 2017/7/23.
 * description  : retrofit 工具
 * version      : 1.0.0
 * version      : 1.0.1 : 获取AppService时，增加泛型方法；
 */
public class ApiServiceUtils {

    public static ApiService getAPIService() {
        return RetrofitManager.getRetrofit(OkHttpClientManager.getOkHttpClient()).create(ApiService.class);
    }

    /**
     * 获取AppService时，增加泛型方法；
     *
     * @param tClass Retrofit turns your HTTP API into a Java interface.
     * @return T
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static <T> T getAPIService(Class<T> tClass) {
        return RetrofitManager.getRetrofit(OkHttpClientManager.getOkHttpClient()).create(tClass);
    }


}
