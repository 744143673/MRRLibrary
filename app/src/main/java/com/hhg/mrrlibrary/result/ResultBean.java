package com.hhg.mrrlibrary.result;


/**
 * Created by hhg on 2017/7/23.
 * description  : 通用的返回结果实体类
 * version      : 1.0.0
 */

public class ResultBean<T> {
    private String status;
    private T data;
    private String msg;

    public ResultBean(String status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess(String code){
        return getStatus().equals(code);
    }
}
