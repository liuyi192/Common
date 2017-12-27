package com.xiaozhu.library.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * @说明 设置TextView图标
 * @作者 LY
 * @时间 2017/11/2 14:05
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class TextDrawableUtils {
    private static TextDrawableUtils instance;
    private static Context mContext;

    public static TextDrawableUtils getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            synchronized (TextDrawableUtils.class) {
                if (instance == null) {
                    instance = new TextDrawableUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 设置TextView左边图标
     *
     * @param textView     控件
     * @param imgResources 图标资源
     */
    public void setDrawableLeft(TextView textView, int imgResources) {
        textView.setCompoundDrawables(getDrawableResources(imgResources), null, null, null);
    }

    /**
     * 设置TextView头部图标
     *
     * @param textView     控件
     * @param imgResources 图标资源
     */
    public void setDrawableTop(TextView textView, int imgResources) {
        textView.setCompoundDrawables(null, getDrawableResources(imgResources), null, null);
    }

    /**
     * 设置TextView头部图标
     *
     * @param textView     控件
     * @param imgResources 图标资源
     */
    public void setDrawableRight(TextView textView, int imgResources) {
        textView.setCompoundDrawables(null, null, getDrawableResources(imgResources), null);
    }

    /**
     * 设置TextView头部图标
     *
     * @param textView     控件
     * @param imgResources 图标资源
     */
    public void setDrawableBottom(TextView textView, int imgResources) {
        textView.setCompoundDrawables(null, null, null, getDrawableResources(imgResources));
    }

    /**
     * 获取图片资源
     *
     * @param imgResources 图片资源
     * @return
     */
    private Drawable getDrawableResources(int imgResources) {
        if (imgResources > 0) {
            Drawable drawable = mContext.getResources().getDrawable(imgResources);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            return drawable;
        }
        return null;
    }
}
