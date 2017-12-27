package com.xiaozhu.library.interfaces;

import android.support.v7.widget.RecyclerView;

import com.xiaozhu.library.adapters.BaseRecyclerAdapter;
import com.xiaozhu.refresh.util.RefreshModel;

/**
 * @说明 Recycle基类方法
 * @作者 LY
 * @时间 2017/12/20 14:48
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface BaseRecycleInterface<T> {

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
     * 列表点击事件
     *
     * @param t        数据对象
     * @param position 第几列
     */
    void itemClick(T t, int position);

    /**
     * 获取数据源
     *
     * @return
     */
    BaseRecyclerAdapter getAdapter();

    /**
     * 获取展示方法
     *
     * @return
     */
    RecyclerView.LayoutManager getLayoutManager();
}
