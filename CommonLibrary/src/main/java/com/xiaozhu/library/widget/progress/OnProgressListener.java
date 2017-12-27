package com.xiaozhu.library.widget.progress;

import com.bumptech.glide.load.engine.GlideException;

/**
 * @说明 图片加载监听
 * @作者 LY
 * @时间 2017/7/30 下午12:18
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface OnProgressListener {

    void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception);
}
