package com.hhg.mrrlibrary.result;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    :
 * version : 1.0.0
 */

public enum EStatus {
    SUCCESS("00", "访问成功");
    private String value;
    private String msg;

    EStatus(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
