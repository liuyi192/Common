package com.xiaozhu.library.http.function;

import com.google.gson.Gson;
import com.xiaozhu.library.http.exception.HttpError;
import com.xiaozhu.library.http.exception.ServerException;
import com.xiaozhu.library.http.retrofit.HttpResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @说明 服务器结果处理函数
 * @作者 LY
 * @时间 2017-09-20 17:14
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class ServerResultFunction implements Function<HttpResponse, Object> {
    @Override
    public Object apply(@NonNull HttpResponse response) throws Exception {
        if (!response.isSuccess()) {
            throw new ServerException(HttpError.HTTP_EXCEPTION.getType(), response.getMsg());
        }
        return new Gson().toJson(response.getData());
    }
}
