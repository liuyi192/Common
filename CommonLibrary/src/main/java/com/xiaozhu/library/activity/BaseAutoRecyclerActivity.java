package com.xiaozhu.library.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaozhu.library.R;
import com.xiaozhu.library.adapters.auto.AutoRecyclerAdapter;
import com.xiaozhu.library.interfaces.BaseAutoRecycleInterface;
import com.xiaozhu.library.listeners.OnRecyclerItemClickListener;
import com.xiaozhu.library.widget.custom.TitleBarView;
import com.xiaozhu.library.widget.loading.LoadingDataView;
import com.xiaozhu.refresh.SmartRefreshLayout;
import com.xiaozhu.refresh.api.RefreshLayout;
import com.xiaozhu.refresh.constant.RefreshState;
import com.xiaozhu.refresh.listener.OnRefreshLoadmoreListener;
import com.xiaozhu.refresh.listener.SimpleMultiPurposeListener;
import com.xiaozhu.refresh.util.RefreshModel;
import com.xiaozhu.refresh.util.RefreshUtils;

/**
 * @说明 基础自动布局
 * @作者 LY
 * @时间 2018/2/25 17:11
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public abstract class BaseAutoRecyclerActivity extends BaseActivity implements BaseAutoRecycleInterface {
    protected TitleBarView titleBar;
    protected SmartRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected LoadingDataView loadingView;
    protected AutoRecyclerAdapter autoRecyclerAdapter;
    protected int pageIndex = 0;

    @Override
    public void initView() {
        titleBar = this.findViewById(R.id.titleBar);
        refreshLayout = this.findViewById(R.id.refreshLayout);
        recyclerView = this.findViewById(R.id.recyclerView);
        loadingView = this.findViewById(R.id.loadingView);
        //设置刷新控件主题
        RefreshUtils.getInstance().init(this, refreshLayout, getRefreshModel());
        //获取Adapter
        autoRecyclerAdapter = new AutoRecyclerAdapter();
        recyclerView.setLayoutManager(getLayoutManager() == null ? new LinearLayoutManager(this) : getLayoutManager());
        if (autoRecyclerAdapter != null) {
            recyclerView.setAdapter(autoRecyclerAdapter);
        }
        //设置默认页码
        pageIndex = getResources().getInteger(R.integer.default_page);
        //设置刷新监听
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //加载更多
                loadingData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //刷新
                if (!refreshlayout.isLoading()) {
                    pageIndex = getResources().getInteger(R.integer.default_page);
                    startRefresh();
                    loadingData();
                }
            }
        });
        //刷新状态监听
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
                if (oldState == RefreshState.LoadFinish && newState == RefreshState.None) { //加载完成
                    stopRefresh();
                } else if (oldState == RefreshState.RefreshFinish && newState == RefreshState.None) {//刷新完成
                    stopRefresh();
                }
            }
        });
    }

    @Override
    public void business() {

    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_base_recycler;
    }

    @Override
    public void startRefresh() {
        autoRecyclerAdapter.clear();
        refreshLayout.autoRefresh();
    }

    @Override
    public void stopRefresh() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        if (autoRecyclerAdapter.getDataList().size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
        }
        if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (recyclerView.isComputingLayout() == false)) {
            autoRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public RefreshModel getRefreshModel() {
        return RefreshModel.PULL_FROM_START;
    }
}
