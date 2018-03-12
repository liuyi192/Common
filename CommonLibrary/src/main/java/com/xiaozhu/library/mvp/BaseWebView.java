package com.xiaozhu.library.mvp;

/**
 * @说明
 * @作者 LY
 * @时间 2018/3/12 9:43
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public interface BaseWebView {
    /**
     * 加载地址
     *
     * @return 加载网页地址
     */
    String getLoadUrl();

    Object getJavascriptClass();
}
