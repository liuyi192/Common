package com.xiaozhu.library.eventBus;

import com.xiaozhu.library.entity.EventBusEntity;

/**
 * @说明 事件监听方法
 * @作者 LY
 * @时间 2016/9/26 14:44
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2016 LY-版权所有
 * @备注
 */
public interface EventBusInterface {

    /**
     * 注册监听
     *
     * @param object
     */
    void registerEventBus(Object object);

    /**
     * 注销注册监听
     *
     * @param object
     */
    void unregister(Object object);

    /**
     * 发送一个广播
     *
     * @param eventBusEntity
     */
    void sendEventBus(EventBusEntity eventBusEntity);

    /**
     * 发送一个广播
     *
     * @param type 类型
     */
    void sendEventBus(int type);

    /**
     * 发送一个广播
     *
     * @param type   类型
     * @param object 传递数据
     */
    void sendEventBus(int type, Object object);
}
