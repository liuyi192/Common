package com.xiaozhu.library.widget.wheel;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * @说明 滚轮控件适配器
 * @作者 LY
 * @文件 WheelViewAdapter.java
 * @时间 2015年6月19日 下午1:57:32
 * @版权 Copyright(c) 2014 LY-版权所有
 */
public interface WheelViewAdapter {
	public int getItemsCount();

	public View getItem(int index, View convertView, ViewGroup parent);

	public View getEmptyItem(View convertView, ViewGroup parent);

	public void registerDataSetObserver(DataSetObserver observer);

	void unregisterDataSetObserver(DataSetObserver observer);
}
