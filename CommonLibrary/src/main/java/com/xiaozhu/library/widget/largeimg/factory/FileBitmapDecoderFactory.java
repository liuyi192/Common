package com.xiaozhu.library.widget.largeimg.factory;

import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;

import java.io.File;
import java.io.IOException;
/**
 * @说明
 * @作者 liuyi
 * @时间 2018/3/27 12:17
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class FileBitmapDecoderFactory implements BitmapDecoderFactory {
    private String path;

    public FileBitmapDecoderFactory(String filePath) {
        super();
        this.path = filePath;
    }

    public FileBitmapDecoderFactory(File file) {
        super();
        this.path = file.getAbsolutePath();
    }

    @Override
    public BitmapRegionDecoder made() throws IOException {
        return BitmapRegionDecoder.newInstance(path, false);
    }

    @Override
    public int[] getImageInfo() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        return new int[]{options.outWidth, options.outHeight};
    }
}