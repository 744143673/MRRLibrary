package com.hhg.mrrlibrary.comm;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/11.
 * desc    : 常量类
 * version : 1.0.0
 */
public class Constant {

    /**
     * http 相关常量定义
     */
    public static class HttpConstant {
        public static String IP = "www.baidu.com";
        public static String PORT = "8087";
        public static String base_url = "http://" + IP + ":" + PORT + "/ScUmsPay/";

        private static final int TIME_OUT = 5; //超时时间  60秒
        public static final int CONNECT_TIME_OUT = TIME_OUT;
        public static final int READ_TIME_OUT = TIME_OUT;
        public static final int WRITE_TIME_OUT = TIME_OUT;
    }

    /**
     * 倒计时控件
     */
    public interface CountDownTimerConstant {
        long millisInFuture = 60 * 1000; // 总时间
        long countDownInterval = 1000;//间隔时间
    }

}
