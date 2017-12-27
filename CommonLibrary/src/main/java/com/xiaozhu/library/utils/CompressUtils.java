package com.xiaozhu.library.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.xiaozhu.library.file.FileManagerUtils;
import com.xiaozhu.library.interfaces.CompressFileCallBack;
import com.xiaozhu.library.interfaces.CompressFileListCallBack;

import net.bither.util.CompressTools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @说明 图片压缩工具类
 * @作者 LY
 * @时间 2017/12/26 14:48
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class CompressUtils {
    public static CompressUtils instance;

    public static CompressUtils getInstance() {
        if (instance == null) {
            synchronized (CompressUtils.class) {
                if (instance == null) {
                    instance = new CompressUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 压缩多张图片
     * @param context
     * @param fileList
     * @param compressFileCallBack
     */
    public void compressFiles(Context context, final List<String> fileList, final CompressFileListCallBack compressFileCallBack) {
        final List<String> compres = new ArrayList<>();
        for (int i = 0; i <fileList.size() ; i++) {
            compressFile(context, fileList.get(i), new CompressFileCallBack() {
                @Override
                public void onStart() {
                    compressFileCallBack.onStart();
                }

                @Override
                public void onFail(String msg) {
                    compressFileCallBack.onFail(msg);
                }

                @Override
                public void onSuccess(String filePath) {
                    compres.add(filePath);
                    if (compres.size() == fileList.size() && compressFileCallBack != null) {
                        compressFileCallBack.onSuccess(compres);
                    }
                }
            });
        }
    }

    /**
     * 单张图片压缩
     *
     * @param context
     * @param filePath
     */
    public void compressFile(Context context, final String filePath, final CompressFileCallBack compressFileCallBack) {
         CompressTools.newBuilder(context)
                .setQuality(80) // 默认压缩质量为60,60足够清晰
                .setKeepResolution(true)//设置保持原图分辨率，则设置的最大宽高就无效了。不需要设置最大宽高了。设置也不会报错了，该参数默认false
                .setFileName(String.valueOf(System.currentTimeMillis()))
                .setDestinationDirectoryPath(FileManagerUtils.getInstance().getImgFolder())
                .build()
                .compressToFile(new File(filePath), new CompressTools.OnCompressListener() {
                    @Override
                    public void onStart() {
                        compressFileCallBack.onStart();
                    }

                    @Override
                    public void onFail(String error) {
                        compressFileCallBack.onFail(error);
                    }

                    @Override
                    public void onSuccess(File file) {
                        compressFileCallBack.onFail(file.getPath());
                    }
                });
    }
}
