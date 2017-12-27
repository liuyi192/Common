package com.xiaozhu.library.http.function;

import com.google.gson.Gson;
import com.xiaozhu.library.http.exception.HttpError;
import com.xiaozhu.library.http.exception.ServerException;


import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @说明 服务器结果处理函数String
 * @作者 LY
 * @时间 2017-09-20 17:14
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class ServerResultObjectFunction implements Function<Object, Object> {
    @Override
    public Object apply(@NonNull Object response) throws Exception {
        if (response == null) {
            throw new ServerException(HttpError.HTTP_EXCEPTION.getType(), HttpError.HTTP_EXCEPTION.getName());
        }
        return new Gson().toJson(response);
    }
}

