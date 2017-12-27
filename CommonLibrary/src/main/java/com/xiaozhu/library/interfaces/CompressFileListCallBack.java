package com.xiaozhu.library.interfaces;

import java.util.List;

/**
 * @说明 压缩图片
 * @作者 LY
 * @时间 2017/12/1 下午2:45
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface CompressFileListCallBack {
    void onStart();

    void onFail(String msg);

    void onSuccess(List<String> list);
}
