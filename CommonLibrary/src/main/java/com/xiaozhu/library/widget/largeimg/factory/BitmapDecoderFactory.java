package com.xiaozhu.library.widget.largeimg.factory;

import android.graphics.BitmapRegionDecoder;

import java.io.IOException;
/**
 * @说明
 * @作者 liuyi
 * @时间 2018/3/27 12:17
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface BitmapDecoderFactory {
    BitmapRegionDecoder made() throws IOException;
    int[] getImageInfo();
}