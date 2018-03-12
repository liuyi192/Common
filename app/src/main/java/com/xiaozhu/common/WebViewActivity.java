package com.xiaozhu.common;

import android.view.View;

import com.xiaozhu.library.activity.BaseWebViewActivity;

/**
 * @说明
 * @作者 LY
 * @时间 2018/3/12 9:53
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class WebViewActivity extends BaseWebViewActivity {

    @Override
    public void initView() {
        super.initView();
        titleBar.setTitle(R.string.app_name);
        titleBar.setBtnLeft(R.mipmap.icon_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public String getLoadUrl() {
        return "https://www.baidu.com/";
    }

    @Override
    public Object getJavascriptClass() {
        return null;
    }
}
