package com.xiaozhu.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * @说明 基础对话框
 * @作者 LY
 * @时间 2017/12/20 15:03
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public abstract class BaseDialog extends Dialog {
    public Context mContext;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        addView();
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        addView();
    }

    private void addView() {
        View view = LayoutInflater.from(mContext).inflate(getLayoutResID(), null);
        initView(view);
        //设置没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置背景颜色
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        addContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public abstract void initView(View view);

    public abstract int getLayoutResID();
}
