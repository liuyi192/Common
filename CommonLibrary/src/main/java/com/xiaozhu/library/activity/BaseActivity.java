package com.xiaozhu.library.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.xiaozhu.library.R;
import com.xiaozhu.library.entity.EventBusEntity;
import com.xiaozhu.library.eventBus.EventBusUtils;
import com.xiaozhu.library.interfaces.BaseViewInterface;
import com.xiaozhu.library.mvp.BaseView;
import com.xiaozhu.library.utils.ActivityManger;
import com.xiaozhu.library.widget.dialog.LoadingDialog;
import com.xiaozhu.umeng.UmengUtils;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @说明 Activity基类
 * @作者 LY
 * @时间 2017/12/19 11:30
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseViewInterface, BaseView {
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        //注册监听
        EventBusUtils.getInstance().registerEventBus(this);
        //把当前Activity加入堆栈内
        ActivityManger.getInstance().addActivity(this);
        //界面方法
        initView();
        business();
    }

    @Override
    public void showProgress() {
        showProgress(getResources().getString(R.string.loading_data));
    }

    @Override
    public void showProgress(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
            loadingDialog.show();
            loadingDialog.setLoadingMsg(TextUtils.isEmpty(msg) ? getResources().getString(R.string.loading_data) : msg);
        }
    }

    @Override
    public void hideProgress() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMainEventBus(EventBusEntity eventBusEntity) {
    }

    @Subscribe(threadMode = ThreadMode.Async)
    public void onAsyncEventBus(final EventBusEntity eventBusEntity) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        UmengUtils.getInstance().onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        UmengUtils.getInstance().onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity堆栈去掉当前
        ActivityManger.getInstance().finishActivity(this);
        //关闭消息监听
        EventBusUtils.getInstance().unregister(this);
    }

    public void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
