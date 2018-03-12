package com.xiaozhu.library.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.xiaozhu.library.R;
import com.xiaozhu.library.activity.ExceptionActivity;
import com.xiaozhu.library.crash.CrashHandlerUtils;
import com.xiaozhu.library.db.DatabaseManager;
import com.xiaozhu.library.entity.BaseEntity;
import com.xiaozhu.library.file.FileManagerUtils;
import com.xiaozhu.umeng.UmengUtils;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @说明 应用配置信息
 * @作者 LY
 * @时间 2017/7/28 12:22
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication instance;
    private static Context mContext;

    public static BaseApplication getInstance() {
        if (instance == null) {
            synchronized (BaseApplication.class) {
                if (instance == null) {
                    instance = new BaseApplication();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        MultiDex.install(this);
        //友盟初始化
        UmengUtils.getInstance().init(this, getResources().getString(R.string.um_app_key), getResources().getString(R.string.um_channel_id));
        //设置文件夹路径
        FileManagerUtils.getInstance().setFolderName(getResources().getString(R.string.file_path));
        //异常捕获
        crashInit();
        //數據庫
        DatabaseManager.getInstance().init(this);
    }

    public Context getAppContext() {
        return mContext;
    }

    public HttpLoggingInterceptor.Level getLevelType() {
        return HttpLoggingInterceptor.Level.BODY;
    }

    /**
     * 捕获异常初始化
     */
    private void crashInit() {
        CrashHandlerUtils.init(new CrashHandlerUtils.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                final String errorInfo = CrashHandlerUtils.saveException(getApplicationContext(), e);
                FileManagerUtils.getInstance().saveText(errorInfo);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        UmengUtils.getInstance().sendError(BaseApplication.this, errorInfo);
                        ExceptionActivity.startException(BaseApplication.this);
                    }
                });
            }
        });
    }
}
