package com.xiaozhu.library.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaozhu.library.R;
import com.xiaozhu.library.adapters.BaseRecyclerAdapter;
import com.xiaozhu.library.interfaces.BaseRecycleInterface;
import com.xiaozhu.library.listeners.OnRecyclerItemClickListener;
import com.xiaozhu.library.widget.loading.LoadingDataView;
import com.xiaozhu.refresh.SmartRefreshLayout;
import com.xiaozhu.refresh.api.RefreshLayout;
import com.xiaozhu.refresh.constant.RefreshState;
import com.xiaozhu.refresh.listener.OnRefreshLoadmoreListener;
import com.xiaozhu.refresh.listener.SimpleMultiPurposeListener;
import com.xiaozhu.refresh.util.RefreshModel;
import com.xiaozhu.refresh.util.RefreshUtils;

/**
 * @说明 基础列表实现
 * @作者 LY
 * @时间 2017/12/21 9:36
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public abstract class BaseRecyclerFragment<T> extends BaseFragment implements BaseRecycleInterface {
    protected RecyclerView recyclerView;
    protected LoadingDataView loadingView;
    protected SmartRefreshLayout refreshLayout;
    protected int pageIndex = 0;//加载更多
    protected BaseRecyclerAdapter adapter;

    @Override
    public void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        loadingView = view.findViewById(R.id.loadingView);
        refreshLayout = view.findViewById(R.id.refreshLayout);

        //设置刷新控件主题
        RefreshUtils.getInstance().init(getActivity(), refreshLayout, getRefreshModel());
        adapter = getAdapter();
        recyclerView.setLayoutManager(getLayoutManager() == null ? new LinearLayoutManager(getActivity()) : getLayoutManager());
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
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
                startRefresh();
                loadingData();
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
        //点击事件
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(View itemView, int position) {
                if (itemView != null) {
                    itemClick(adapter.getItem(position), position);
                }
            }
        });
    }

    @Override
    public int getLayoutResID() {
        return R.layout.common_base_recycler;
    }

    @Override
    public void startRefresh() {
        adapter.clear();
        pageIndex = getResources().getInteger(R.integer.default_page);
        refreshLayout.autoRefresh();
    }

    @Override
    public void stopRefresh() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        if (adapter.getListData().size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public RefreshModel getRefreshModel() {
        return RefreshModel.PULL_FROM_START;
    }
}
