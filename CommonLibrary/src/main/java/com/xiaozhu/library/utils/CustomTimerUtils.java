package com.xiaozhu.library.utils;

import android.os.CountDownTimer;

/**
 * @说明 自定义定时器
 * @作者 LY
 * @时间 2017/10/26 下午8:40
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class CustomTimerUtils extends CountDownTimer {
    private CustomTimerListener customTimerListener;

    public CustomTimerUtils(long millisInFuture, long countDownInterval) {
        super(millisInFuture * 1000, countDownInterval * 1000);
    }

    public void setCustomTimerListener(CustomTimerListener customTimerListener) {
        this.customTimerListener = customTimerListener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        customTimerListener.start(millisUntilFinished / 1000);
    }

    @Override
    public void onFinish() {
        customTimerListener.finish();
    }

    public interface CustomTimerListener {
        void start(long time);

        void finish();
    }

}
