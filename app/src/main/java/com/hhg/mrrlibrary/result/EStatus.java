package com.hhg.mrrlibrary.result;

/**
 * author  : hhg
 * e-mail  : hhg673@foxmail.com
 * date    : 2017/9/1.
 * desc    :
 * version : 1.0.0
 */

public enum EStatus {
    SUCCESS("00", "访问成功"),
    Fail("01", "访问失败"),
    PARAMS_ERROR("02", "参数不正确"),
    USER_NO_EXIST("03", "用户名不存在"),
    USER_PWD_ERROR("04", "密码错误"),
    TOKEN_INVALID("05", "token无效"),
    TEL_CODE_ERROR("06", "验证码错误"),
    USER_EXIST("07", "用户已存在"),
    TEL_INCORRECT("08", "手机号格式不正确"),
    PWD_PATTERN_ERROR("09", "密码格式不正确"),
    USER_OR_PWD_ERROR("10", "用户名或密码不正确"),
    TEL_CODE_TIMEOUT("11", "验证码超时"),
    TEL_CODE_TYPE_ERROR("12", "验证码TYPE错误"),
    TEL_CODE_USED("13", "验证码无效(已使用)");
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
