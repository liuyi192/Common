package com.xiaozhu.library.widget.loading;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaozhu.library.R;
import com.xiaozhu.refresh.footer.ballpulse.BallPulseView;

/**
 * @说明 加载数据中...
 * @作者 LY
 * @时间 2017-09-20 14:37
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 XQJY-版权所有
 * @备注
 */
public class LoadingDataView extends FrameLayout {
    private Context mContext;
    private BallPulseView loading;
    private ImageView icon;
    private TextView tvInfo;
    private TextView btnClick;

    public LoadingDataView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    /**
     * 加载中...
     */
    public void loading() {
        loading(R.string.loading_data);
    }

    /**
     * 加载中...
     *
     * @param msg
     */
    public void loading(int msg) {
        icon.setVisibility(GONE);
        tvInfo.setText(msg);
        loading.setVisibility(VISIBLE);
        loading.startAnim();
    }

    /**
     * 数据加载空
     */
    public void loadingDataEmpty() {
        loadingDataEmpty(0, R.string.data_empty);
    }

    /**
     * 加载数据为空的情况
     *
     * @param iconRes
     * @param msgRes
     */
    public void loadingDataEmpty(int iconRes, int msgRes) {
        loading.setVisibility(GONE);
        loading.stopAnim();
        icon.setVisibility(VISIBLE);
        if (iconRes > 0) {
            icon.setImageResource(iconRes);
        }
        if (msgRes > 0) {
            tvInfo.setText(msgRes);
        }
        loadingDataEmpty(iconRes, getResources().getString(msgRes));
    }

    /**
     * 加载数据为空的情况
     *
     * @param iconRes
     * @param msgRes
     */
    public void loadingDataEmpty(int iconRes, String msgRes) {
        loading.setVisibility(GONE);
        loading.stopAnim();
        icon.setVisibility(VISIBLE);
        if (iconRes > 0) {
            icon.setImageResource(iconRes);
        }
        if (!TextUtils.isEmpty(msgRes)) {
            tvInfo.setText(msgRes);
        }
    }


    /**
     * 网络错误
     */
    public void networkError() {
        loading.setVisibility(GONE);
        loading.stopAnim();
        icon.setVisibility(VISIBLE);
        icon.setImageResource(R.mipmap.net_error);
        tvInfo.setText(R.string.net_error);
    }

    public TextView getBtnClick() {
        return btnClick;
    }

    /**
     * 初始化布局
     */
    private void initView() {
        View loadingView = LayoutInflater.from(mContext).inflate(R.layout.common_empty_view, null);
        loading = (BallPulseView) loadingView.findViewById(R.id.ballPulse);
        icon = (ImageView) loadingView.findViewById(R.id.icon);
        tvInfo = (TextView) loadingView.findViewById(R.id.tvInfo);
        btnClick = (TextView) loadingView.findViewById(R.id.btnClick);
        loading.setIndicatorColor(mContext.getResources().getColor(R.color.common_color));
        loading.setNormalColor(mContext.getResources().getColor(R.color.common_color));
        loading.setAnimatingColor(mContext.getResources().getColor(R.color.common_color));
        loading.startAnim();
        addView(loadingView);
    }
}
