package com.xiaozhu.library.fragment;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xiaozhu.library.R;
import com.xiaozhu.library.activity.BaseActivity;
import com.xiaozhu.library.mvp.BaseWebView;
import com.xiaozhu.library.utils.AppUtils;
import com.xiaozhu.library.utils.NetWorkUtils;
import com.xiaozhu.library.widget.custom.TitleBarView;
import com.xiaozhu.library.widget.loading.LoadingDataView;
import com.xiaozhu.library.widget.web.WVJBWebView;
import com.xiaozhu.library.widget.web.WVJBWebViewClient;

/**
 * @说明 基础网页工具
 * @作者 LY
 * @时间 2018/3/12 9:22
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public abstract class BaseWebViewFragment extends BaseFragment implements BaseWebView {
    protected WVJBWebView webView;
    protected LoadingDataView loadingView;

    @Override
    public void initView(View view) {
        webView = view.findViewById(R.id.webView);
        loadingView = view.findViewById(R.id.loadingView);
        //设置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString() + "_" + getString(R.string.web_user_agent) + "@" + AppUtils.getVersionName(getActivity()));
        //网页适配HTML5
        GeoClient geo = new GeoClient();
        webView.setWebChromeClient(geo);
        webView.addJavascriptInterface(getJavascriptClass(), getResources().getString(R.string.javascript_name));
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WVJBWebViewClient(webView));
        String origin = "";
        geo.onGeolocationPermissionsShowPrompt(origin, new GeolocationPermissions.Callback() {
            @Override
            public void invoke(String origin, boolean allow, boolean retain) {

            }
        });
        //网页监听
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    loadingView.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                } else {
                    loadingView.setVisibility(View.VISIBLE);
                    webView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void business() {
        loadingView.setVisibility(View.VISIBLE);
        if (NetWorkUtils.getInstance().getNetworkStatus(getActivity())) {
            webView.loadUrl(getLoadUrl());
            loadingView.loading();
        } else {
            loadingView.loadingDataEmpty(R.mipmap.net_error, R.string.net_error);
        }
    }

    @Override
    public int getLayoutResID() {
        return R.layout.common_base_web;
    }

    public class GeoClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
            callback.invoke(origin, true, false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        synCookies(getActivity());
        webView.reload();
    }

    public void synCookies(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        CookieSyncManager.getInstance().sync();
    }
}
