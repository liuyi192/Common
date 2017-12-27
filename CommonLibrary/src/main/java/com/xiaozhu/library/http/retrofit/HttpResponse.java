package com.xiaozhu.library.http.retrofit;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * @说明 http响应参数实体类 通过Gson解析属性名称需要与服务器返回字段对应,或者使用注解@SerializedName
 * @作者 LY
 * @时间 2017-09-20 17:14
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注 这里与服务器约定返回格式
 */
public class HttpResponse<T> {
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private T data;
    @SerializedName("msg")
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    @Override
    public String toString() {
        return "HttpResponse{" +
                "success=" + success +
                ", data=" + (new Gson().toJson(data)) +
                ", msg='" + msg + '\'' +
                '}';
    }
}
