package com.hhg.mrrlibrary.comm;
import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/11.
 * desc    : 定义网络访问接口
 * version : 1.0.0
 */

public interface ApiService {

    @POST("LsStaffChkServlet")
    Flowable<String> login(@Query("id") String id, @Query("data") String data);

}
