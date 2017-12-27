package com.xiaozhu.library.widget.web;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @说明
 * @作者 LY
 * @时间 2017/11/13 19:28
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class WVJBWebViewClient extends WebViewClient {
    private WVJBWebView mWVJBWebView;

    public WVJBWebViewClient(WVJBWebView wvjbWebView) {
        mWVJBWebView = wvjbWebView;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(WVJBConstants.SCHEME)) {
            if (url.indexOf(WVJBConstants.BRIDGE_LOADED) > 0) {
                mWVJBWebView.injectJavascriptFile();
            } else if (url.indexOf(WVJBConstants.MESSAGE) > 0) {
                mWVJBWebView.flushMessageQueue();
            }
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

}