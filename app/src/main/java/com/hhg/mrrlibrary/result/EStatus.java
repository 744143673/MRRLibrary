package com.hhg.mrrlibrary.result;

import android.support.annotation.NonNull;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    : 请求状态码  枚举
 * version : 1.0.0
 */

public enum EStatus {
    SUCCESS("00", "获取数据成功"),
    FAIL("01", "获取数据失败"),
    UNKNOWN("02", "未知错误");


    private String value;
    private String dec;

    EStatus(@NonNull String value, @NonNull String dec) {
        this.value = value;
        this.dec = dec;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(@NonNull String dec) {
        this.dec = dec;
    }

    public String getValue() {
        return value;
    }

    public void setValue(@NonNull String value) {
        this.value = value;
    }

}
