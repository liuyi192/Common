package com.xiaozhu.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.xiaozhu.library.R;

import java.io.File;

/**
 * @说明 程序工具
 * @作者 LY
 * @时间 2017/7/28 15:04
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class AppUtils {
    /**
     * 获取资源文件id
     *
     * @param mContext 上下文
     * @param resType  资源类型（drawable/string/layout/style/dimen/color/array等）
     * @param resName  资源文件名称
     * @return
     */
    public static int getResId(Context mContext, String resType, String resName) {
        int result = -1;
        try {
            String packageName = mContext.getPackageName();
            result = mContext.getResources().getIdentifier(resName, resType, packageName);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * 返回版本名字 对应build.gradle中的versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 返回版本号 对应build.gradle中的versionCode
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取设备宽度（px）
     *
     * @param context
     * @return
     */
    public static int deviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备高度（px）
     *
     * @param context
     * @return
     */
    public static int deviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取视频封面
     *
     * @param videoPath 视频地址
     * @return
     */
    public static String getVideoCover(String videoPath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(videoPath);
        return ImageUtil.saveMyBitmap(mmr.getFrameAtTime());
    }

    /**
     * 拨打电话
     *
     * @param context
     * @param phone   电话号码
     */
    public static void callPhone(Context context, String phone) {
        Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone));
        context.startActivity(phoneIntent);
    }

    /**
     * 拍照
     *
     * @param activity
     * @param imagePath
     */
    public static void letCamera(Activity activity, String imagePath, int requestCode) {
        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File out = new File(imagePath);
        Uri uri = getUri(activity, out);
        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(imageCaptureIntent, requestCode);
    }

    public static Uri getFileUri(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getResources().getString(R.string.app_package) + ".fileprovider", file);
    }

    public static Uri getUri(Context context, File file) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT <= 23) {
            uri = Uri.fromFile(file);
        } else {
            uri = getFileUri(context, file);
        }
        return uri;
    }

    /**
     * 获取SDK版本
     *
     * @return
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

}
