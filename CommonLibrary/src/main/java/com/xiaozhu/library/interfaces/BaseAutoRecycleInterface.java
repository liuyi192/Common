package com.xiaozhu.library.interfaces;

import android.support.v7.widget.RecyclerView;

import com.xiaozhu.library.adapters.BaseRecyclerAdapter;
import com.xiaozhu.library.adapters.auto.AutoRecyclerAdapter;
import com.xiaozhu.refresh.util.RefreshModel;

/**
 * @说明 基础自动布局
 * @作者 LY
 * @时间 2018/2/25 17:16
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public interface BaseAutoRecycleInterface {
    /**
     * 加载数据
     */
    void loadingData();

    /**
     * 开始刷新
     */
    void startRefresh();

    /**
     * 结束刷新
     */
    void stopRefresh();

    /**
     * 界面熟悉类型
     *
     * @return
     */
    RefreshModel getRefreshModel();

    /**
     * 获取展示方法
     *
     * @return
     */
    RecyclerView.LayoutManager getLayoutManager();
}
