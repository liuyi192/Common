package com.xiaozhu.library.utils;

import android.util.Log;

/**
 * @说明 日志输出工具
 * @作者 LY
 * @时间 2017/7/28 下午9:26
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class LogUtil {
    private static final String TAG = "INFO";

    /**
     * 输出信息
     *
     * @param msg 消息
     */
    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 输出测试消息
     *
     * @param msg 消息
     */
    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    /**
     * 输出错误消息
     *
     * @param msg       消息
     * @param throwable 异常
     */
    public static void e(String msg, Throwable throwable) {
        Log.e(TAG, msg, throwable);
    }
}
