package com.xiaozhu.library.http.exception;

/**
 * @说明 api接口错误/异常统一处理类
 * @作者 LY
 * @时间 2017-09-20 16:05
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class ApiException extends Exception {
    //错误码
    private int code;
    //错误信息
    private String msg;

    public ApiException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
