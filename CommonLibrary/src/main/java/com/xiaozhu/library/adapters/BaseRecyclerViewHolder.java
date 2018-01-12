package com.xiaozhu.library.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @说明 基类
 * @作者 LY
 * @时间 2017/10/31 18:54
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(View itemView, int viewType) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
