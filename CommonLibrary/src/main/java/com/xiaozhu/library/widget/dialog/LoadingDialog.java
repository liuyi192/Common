package com.xiaozhu.library.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.xiaozhu.library.R;
import com.xiaozhu.refresh.footer.ballpulse.BallPulseView;

/**
 * @说明 加载进度
 * @作者 LY
 * @时间 2017/10/20 14:33
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class LoadingDialog extends ProgressDialog {
    private TextView tvMessage;
    private Context mContext;
    private BallPulseView ballPulseView;

    public LoadingDialog(Context context) {
        super(context, R.style.customProgressDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_loading);
        tvMessage = (TextView) this.findViewById(R.id.tvMessage);
        ballPulseView = (BallPulseView) this.findViewById(R.id.ballPulse);
        ballPulseView.setIndicatorColor(mContext.getResources().getColor(R.color.common_color));
        ballPulseView.setNormalColor(mContext.getResources().getColor(R.color.common_color));
        ballPulseView.setAnimatingColor(mContext.getResources().getColor(R.color.common_color));
        ballPulseView.startAnim();
        setCancelable(false);
    }

    /**
     * 设置加载信息
     */
    public void setLoadingMsg() {
        setLoadingMsg("");
    }

    /**
     * 设置加载信息
     *
     * @param msgRes 加载信息
     */
    public void setLoadingMsg(int msgRes) {
        if (msgRes > 0) {
            setLoadingMsg(mContext.getResources().getString(msgRes));
        }
    }

    /**
     * 设置加载信息
     *
     * @param msg 加载信息
     */
    public void setLoadingMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            tvMessage.setText(msg);
        }
    }

}
