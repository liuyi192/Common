package com.xiaozhu.library.entity;

import java.io.Serializable;

/**
 * @说明 对象父类
 * @作者 LY
 * @时间 2017/7/28 下午8:18
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class BaseEntity implements Serializable {
    private boolean isLastPage;

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }
}
