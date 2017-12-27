package com.xiaozhu.library.http.exception;

/**
 * @说明 网路异常
 * @作者 LY
 * @时间 2017-09-20 17:00
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public enum HttpError {
    HTTP_EXCEPTION(2000, "网络错误"),
    ANALYTIC_SERVER_DATA_ERROR(1001, "解析错误"),
    CONNECT_ERROR(1003, "连接失败"),
    TIME_OUT_ERROR(1004, "网络超时"),
    UN_KNOWN_ERROR(1000, "未知错误");

    private int type;
    private String name;

    HttpError(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getName(int type) {
        for (HttpError c : HttpError.values()) {
            if (c.getType() == type) {
                return c.getName();
            }
        }
        return HTTP_EXCEPTION.name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
