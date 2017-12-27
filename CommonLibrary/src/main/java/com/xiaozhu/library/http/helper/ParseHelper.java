package com.xiaozhu.library.http.helper;

import com.google.gson.JsonElement;

/**
 * @说明 数据解析helper
 * @作者 LY
 * @时间 2017-09-20 17:14
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface ParseHelper {
    Object[] parse(JsonElement jsonElement);
}
