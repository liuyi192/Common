package com.xiaozhu.library.http.retrofit;

import io.reactivex.disposables.Disposable;

/**
 * @说明 RxJavaAction管理接口
 * @作者 LY
 * @时间 2017-09-20 17:29
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface RxActionManager<T> {
    /**
     * 添加
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 取消
     *
     * @param tag
     */
    void cancel(T tag);

}