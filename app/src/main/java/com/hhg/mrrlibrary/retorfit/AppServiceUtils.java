package com.hhg.mrrlibrary.retorfit;


import com.hhg.mrrlibrary.comm.ApiService;

/**
 * Created by hhg on 2017/7/23.
 * description  : retrofit 封装
 * version      : 1.0.0
 */
public class AppServiceUtils {

    public static ApiService getAppService() {
        return RetrofitManager.getRetrofit(OkHttpClientManager.getOkHttpClient()).create(ApiService.class);
    }
}
