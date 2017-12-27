package com.xiaozhu.library.interfaces;

/**
 * @说明 压缩文件回调
 * @作者 LY
 * @时间 2017/12/26 14:55
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface CompressFileCallBack {
    void onStart();

    void onFail(String msg);

    void onSuccess(String filePath);
}
