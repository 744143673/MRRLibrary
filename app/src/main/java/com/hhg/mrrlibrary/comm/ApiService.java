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

    /**
     * POS通获取系统跟踪号   http://ip:port/xxx/PosTGetRecnoServlet?id=银商分配&data=加密后的字符串
     *
     * @param id   id
     * @param data data
     * @return Flowable
     */
    @POST("PosTGetRecnoServlet")
    Flowable<String> getRecnoTask(@Query("id") String id, @Query("data") String data);


    /**
     * POS通支付请求   http://ip:port/xxx/PosTPayMentServlet?id=银商分配&data=加密后的字符串
     *
     * @param id   id
     * @param data data
     * @return Flowable
     */
    @POST("PosTPayMentServlet")
    Flowable<String> posTPayTask(@Query("id") String id, @Query("data") String data);

    /**
     * 银联支付请求  http://ip:port/xxx/ UnionCodePayServlet?id=银商分配&data=加密后的字符串
     *
     * @param id   id
     * @param data data
     * @return Flowable
     */
    @POST("UnionCodePayServlet")
    Flowable<String> bankTPayTask(@Query("id") String id, @Query("data") String data);


    /**
     * 银联支付请求  http://ip:port/xxx/ UnionCodeQueryServlet?id=银商分配&data=加密后的Json字串
     *
     * @param id   id
     * @param data data
     * @return Flowable
     */
    @POST("UnionCodeQueryServlet")
    Flowable<String> bankQuery(@Query("id") String id, @Query("data") String data);

    /**
     * 通知  http://ip:port/xxx/ UnionLsServlet?id=银商分配&data=加密后的Json字串
     *
     * @param id   id
     * @param data data
     * @return Flowable
     */
    @POST("LsServlet")
    Flowable<String> confirmBill(@Query("id") String id, @Query("data") String data);

}
