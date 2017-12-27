package com.xiaozhu.library.mvp;

/**
 * @说明 基础方法
 * @作者 LY
 * @时间 2017/12/20 15:00
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface BaseView {
    /**
     * 显示加载对话框
     */
    void showProgress();

    /**
     * 显示加载对话框
     */
    void showProgress(String msg);

    /**
     * 隐藏加载对话框
     */
    void hideProgress();
}
