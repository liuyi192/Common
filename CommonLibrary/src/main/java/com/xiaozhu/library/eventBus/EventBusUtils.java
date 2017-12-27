package com.xiaozhu.library.eventBus;

import com.xiaozhu.library.entity.EventBusEntity;

import de.greenrobot.event.EventBus;

/**
 * @说明 事件监听工具类
 * @作者 LY
 * @时间 2016/9/26 14:43
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2016 LY-版权所有
 * @备注
 */
public class EventBusUtils implements EventBusInterface {
    private static EventBusUtils instance;

    public static EventBusUtils getInstance() {
        if (instance == null) {
            synchronized (EventBusUtils.class) {
                if (instance == null) {
                    instance = new EventBusUtils();
                }
            }
        }
        return instance;
    }

    @Override
    public void registerEventBus(Object object) {
        EventBus.getDefault().register(object);
    }

    @Override
    public void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    @Override
    public void sendEventBus(int type) {
        sendEventBus(new EventBusEntity(type, null));
    }

    @Override
    public void sendEventBus(int type, Object object) {
        sendEventBus(new EventBusEntity(type, object));
    }

    @Override
    public void sendEventBus(EventBusEntity eventBusEntity) {
        EventBus.getDefault().post(eventBusEntity);
    }
}
