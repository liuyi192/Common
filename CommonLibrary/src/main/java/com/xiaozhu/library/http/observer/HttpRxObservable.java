package com.xiaozhu.library.http.observer;

import com.xiaozhu.library.http.function.HttpResultFunction;
import com.xiaozhu.library.http.function.ServerResultFunction;
import com.xiaozhu.library.http.function.ServerResultObjectFunction;
import com.xiaozhu.library.http.retrofit.HttpResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @说明 适用Retrofit网络请求Observable(被监听者)
 * @作者 LY
 * @时间 2017-09-20 17:23
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class HttpRxObservable {

    /**
     * 获取被监听者
     * 网络请求Observable构建
     * 网络请求参数
     * 无管理生命周期,容易导致内存溢出
     *
     * @param apiObservable
     * @return
     */
    public static Observable getObservable(Observable<HttpResponse> apiObservable) {
        Observable observable = apiObservable.map(new ServerResultFunction())
                .onErrorResumeNext(new HttpResultFunction<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    /**
     * 获取被监听者
     * 网络请求Observable构建
     * 网络请求参数
     * 无管理生命周期,容易导致内存溢出
     *
     * @param apiObservable
     * @return
     */
    public static Observable getObservableObject(Observable<Object> apiObservable) {
        Observable observable = apiObservable.map(new ServerResultObjectFunction())
                .onErrorResumeNext(new HttpResultFunction<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}
