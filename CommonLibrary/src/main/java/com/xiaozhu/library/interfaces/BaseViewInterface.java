package com.xiaozhu.library.interfaces;

import com.xiaozhu.library.entity.EventBusEntity;

/**
 * @说明 主界面
 * @作者 LY
 * @时间 2017/12/19 11:50
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface BaseViewInterface {
    /**
     * 初始化控件
     */
    void initView();

    /**
     * 业务操作
     */
    void business();

    /**
     * 控件
     *
     * @return
     */
    int getLayoutResID();
}
