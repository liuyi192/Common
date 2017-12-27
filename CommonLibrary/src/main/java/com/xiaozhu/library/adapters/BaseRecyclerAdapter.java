package com.xiaozhu.library.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaozhu.library.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明 基础Recycler数据源
 * @作者 LY
 * @时间 2017/10/31 18:49
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public abstract class BaseRecyclerAdapter<T extends BaseRecyclerViewHolder, E extends BaseEntity> extends RecyclerView.Adapter<T> {
    protected Context mContext;
    protected LayoutInflater inflater;
    protected List<E> listData = new ArrayList<>();

    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据
     *
     * @param listData
     */
    public void setList(List<E> listData) {
        if (listData != null && listData.size() > 0) {
            this.listData.addAll(listData);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     */
    public void addObject(E e) {
        if (null != e) {
            this.listData.add(e);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据
     *
     * @param position
     */
    public void addObject(int position, E e) {
        if (null != e) {
            this.listData.add(position, e);
            notifyDataSetChanged();
        }
    }

    /**
     * 清理数据
     */
    public void clear() {
        this.listData.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除一个
     *
     * @param position
     */
    public void removeItem(int position) {
        this.listData.remove(position);
        notifyItemRemoved(position);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 500);
    }

    public E getItem(int position) {
        return listData == null ? null : listData.get(position);
    }

    public List<E> getListData() {
        return listData;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(getLayoutResID(viewType), parent, false);
        return createView(convertView, viewType);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        bindData(holder, getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public abstract T createView(View convertView, int viewType);

    public abstract void bindData(T holder, E e, int position);

    public abstract int getLayoutResID(int viewType);
}

