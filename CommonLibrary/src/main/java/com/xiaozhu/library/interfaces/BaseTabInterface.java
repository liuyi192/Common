package com.xiaozhu.library.interfaces;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * @说明 头部标签设置
 * @作者 LY
 * @时间 2018/1/17 10:47
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public interface BaseTabInterface {
    void setTabData(List<String> tabName, List<Fragment> listFragment);
}
