package com.xiaozhu.library.crash;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.xiaozhu.library.app.BaseApplication;
import com.xiaozhu.library.utils.AppUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @说明 错误信息捕获写入SD卡
 * @作者 LY
 * @时间 2016/9/22 10:05
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2016 LY-版权所有
 * @备注
 */
public class CrashHandlerUtils {
    private CrashHandler mCrashHandler;
    private static CrashHandlerUtils mInstance;

    private static CrashHandlerUtils getInstance() {
        if (mInstance == null) {
            synchronized (CrashHandlerUtils.class) {
                if (mInstance == null) {
                    mInstance = new CrashHandlerUtils();
                }
            }
        }
        return mInstance;
    }

    public static void init(CrashHandler crashHandler) {
        getInstance().setCrashHandler(crashHandler);
    }

    private void setCrashHandler(CrashHandler crashHandler) {
        mCrashHandler = crashHandler;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Looper.loop();
                    } catch (Throwable e) {
                        if (mCrashHandler != null) {//捕获异常处理
                            mCrashHandler.uncaughtException(Looper.getMainLooper().getThread(), e);
                        }
                    }
                }
            }
        });

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (mCrashHandler != null) {//捕获异常处理
                    mCrashHandler.uncaughtException(t, e);
                }
            }
        });

    }

    public interface CrashHandler {
        void uncaughtException(Thread t, Throwable e);
    }

    /**
     * 保存错误日志
     *
     * @param context
     * @param throwable
     * @return
     */
    public static String saveException(Context context, Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.append("VersionCode：" + AppUtils.getVersionCode(context) + "\n");
        printWriter.append("VersionName：" + AppUtils.getVersionName(context) + "\n");
        printWriter.append("DeviceWidth：" + AppUtils.deviceWidth(context) + "\n");
        printWriter.append("DeviceHeight：" + AppUtils.deviceHeight(context) + "\n");
        printWriter.append("AndroidVersion：" + AppUtils.getSDKVersion() + "\n");
        printWriter.append("CPU_ABI：" + Build.CPU_ABI + "\n");
        printWriter.append("CPU_ABI2：" + Build.CPU_ABI2 + "\n");
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        return writer.toString();
    }
}
