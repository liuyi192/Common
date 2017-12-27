package com.xiaozhu.library.mvp;

/**
 * @说明 设置基类
 * @作者 LY
 * @时间 2017/12/20 15:01
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class BasePresenter {
    private BaseView baseView;

    public BasePresenter(BaseView baseView) {
        this.baseView = baseView;
    }
}
