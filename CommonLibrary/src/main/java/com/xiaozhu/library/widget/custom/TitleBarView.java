package com.xiaozhu.library.widget.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaozhu.library.R;

/**
 * @说明 标题栏
 * @作者 LY
 * @时间 2017/12/19 11:28
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class TitleBarView extends FrameLayout {
    private Context mContext;
    private TextView tvTitle;
    private TextView btnTvRight;
    private ImageView btnLeft;
    private ImageView btnIvRight;
    private LinearLayout lineRight;

    public TitleBarView(@NonNull Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public TitleBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    /**
     * 设置按钮点击事件
     *
     * @param resId
     * @param onClickListener
     */
    public void setBtnLeft(int resId, OnClickListener onClickListener) {
        btnLeft.setImageResource(resId);
        btnLeft.setVisibility(VISIBLE);
        btnLeft.setOnClickListener(onClickListener);
    }

    /**
     * 设置右边文字按钮
     *
     * @param name
     * @param onClickListener
     */
    public void setBtnTvRight(int name, OnClickListener onClickListener) {
        setBtnTvRight(getResources().getString(name), onClickListener);
    }

    /**
     * 设置右边文字按钮
     *
     * @param name
     * @param onClickListener
     */
    public void setBtnTvRight(String name, OnClickListener onClickListener) {
        btnTvRight.setVisibility(VISIBLE);
        btnTvRight.setText(name);
        btnTvRight.setOnClickListener(onClickListener);
    }

    /**
     * 设置按钮名称
     *
     * @param name
     */
    public void setBtnTvRightName(String name) {
        btnTvRight.setText(name);
    }

    /**
     * 设置右边图片按钮
     *
     * @param resId
     * @param onClickListener
     */
    public void setBtnIvRight(int resId, OnClickListener onClickListener) {
        btnIvRight.setVisibility(VISIBLE);
        btnIvRight.setImageResource(resId);
        btnIvRight.setOnClickListener(onClickListener);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(int title) {
        tvTitle.setText(getResources().getString(title));
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        View titleBarView = LayoutInflater.from(mContext).inflate(R.layout.common_title_bar, null);
        tvTitle = (TextView) titleBarView.findViewById(R.id.tvTitle);
        btnLeft = (ImageView) titleBarView.findViewById(R.id.btnLeft);
        lineRight = (LinearLayout) titleBarView.findViewById(R.id.lineRight);
        btnTvRight = (TextView) titleBarView.findViewById(R.id.btnTvRight);
        btnIvRight = (ImageView) titleBarView.findViewById(R.id.btnIvRight);
        addView(titleBarView);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getBtnTvRight() {
        return btnTvRight;
    }
}
