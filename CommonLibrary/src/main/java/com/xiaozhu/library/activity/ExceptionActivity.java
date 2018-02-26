package com.xiaozhu.library.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xiaozhu.library.R;
import com.xiaozhu.library.utils.ActivityManger;


/**
 * @说明 异常界面
 * @作者 LY
 * @时间 2017/12/21 15:14
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class ExceptionActivity extends BaseActivity {
    private TextView btnClose;

    /**
     * 跳转到异常界面
     *
     * @param context
     */
    public static void startException(Context context) {
        Intent intent = new Intent(context, ExceptionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        btnClose = this.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManger.getInstance().AppExit(ExceptionActivity.this);
                finish();
            }
        });
    }

    @Override
    public void business() {

    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_exception;
    }
}
