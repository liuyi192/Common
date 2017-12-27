package com.xiaozhu.library.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhu.library.R;
import com.xiaozhu.library.entity.EventBusEntity;
import com.xiaozhu.library.eventBus.EventBusUtils;
import com.xiaozhu.library.interfaces.BaseFragmentViewInterface;
import com.xiaozhu.library.mvp.BaseView;
import com.xiaozhu.library.widget.dialog.LoadingDialog;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @说明 Fragment基类
 * @作者 LY
 * @时间 2017/12/21 9:26
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public abstract class BaseFragment extends Fragment implements BaseFragmentViewInterface, BaseView {
    private LoadingDialog loadingDialog;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册监听
        EventBusUtils.getInstance().registerEventBus(this);
        //业务处理
        business();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(getLayoutResID(), container, false);
        initView(contentView);
        return contentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //关闭消息监听
        EventBusUtils.getInstance().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMainEventBus(EventBusEntity eventBusEntity) {
        onEventBus(eventBusEntity);
    }

    @Subscribe(threadMode = ThreadMode.Async)
    public void onAsyncEventBus(final EventBusEntity eventBusEntity) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onEventBus(eventBusEntity);
            }
        });
    }

    @Override
    public void showProgress() {
        showProgress(getResources().getString(R.string.loading_data));
    }

    @Override
    public void showProgress(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getActivity());
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
}
