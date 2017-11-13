package com.hhg.mrrlibrary.result;

public class Result<T> {
    private EStatus eStatus;
    private String msg;
    private T data;

    public Result(EStatus eStatus, String msg, T data) {
        this.eStatus = eStatus;
        this.msg = msg;
        this.data = data;
    }

    public EStatus geteStatus() {
        return eStatus;
    }

    public void seteStatus(EStatus eStatus) {
        this.eStatus = eStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
