package com.xiaozhu.library.widget.custom;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaozhu.library.R;
import com.xiaozhu.library.app.BaseApplication;

/**
 * @说明 消息对话框
 * @作者 LY
 * @时间 2017/12/21 11:30
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 显示Toast消息
     *
     * @param msg 消息内容
     */
    public static void showToast(String msg) {
        showCustomToast(0, msg);
    }

    /**
     * 显示消息
     *
     * @param msgId 消息内容
     */
    public static void showToast(int msgId) {
        showCustomToast(0, BaseApplication.getInstance().getAppContext().getResources().getString(msgId));
    }

    /**
     * 显示图片消息
     *
     * @param resId 图片资源
     */
    public static void showIconToast(int resId) {
        showIconToast(resId, null);
    }

    /**
     * 显示图片消息
     *
     * @param resId 图片资源
     * @param msgId 消息内容
     */
    public static void showIconToast(int resId, int msgId) {
        showIconToast(resId, BaseApplication.getInstance().getResources().getString(msgId));
    }


    /**
     * 显示图片消息
     *
     * @param resId 图片资源
     * @param msg   消息内容
     */
    public static void showIconToast(int resId, String msg) {
        showCustomToast(resId, msg);
    }


    /**
     * 显示自定义消息
     *
     * @param resId 图片资源
     * @param msg   消息内容
     */
    private static void showCustomToast(int resId, String msg) {
        View view = LayoutInflater.from(BaseApplication.getInstance().getAppContext()).inflate(R.layout.common_toast_view, null);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        ImageView imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
        if (!TextUtils.isEmpty(msg)) {
            tvName.setText(msg);
            tvName.setVisibility(View.VISIBLE);
        } else {
            tvName.setVisibility(View.GONE);
        }
        if (resId > 0) {
            imgIcon.setImageResource(resId);
            imgIcon.setVisibility(View.VISIBLE);
        } else {
            imgIcon.setVisibility(View.GONE);
        }
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = new Toast(BaseApplication.getInstance());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.show();
    }
}
