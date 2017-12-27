package com.xiaozhu.library.widget.toggle;

/**
 * @说明
 * @作者 LY
 * @时间 2016/12/13 11:01
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2016 LY-版权所有
 * @备注
 */
public class SpringSystem extends BaseSpringSystem {

    public static SpringSystem create() {
        return new SpringSystem(AndroidSpringLooperFactory.createSpringLooper());
    }

    private SpringSystem(SpringLooper springLooper) {
        super(springLooper);
    }
}
