package com.xiaozhu.library.widget.wheel;

/**
 * @说明 滚动适配器
 * @作者 LY
 * @文件 WheelAdapter.java
 * @时间 2015年6月19日 下午1:48:29
 * @版权 Copyright(c) 2014 LY-版权所有
 */
public interface WheelAdapter {
	public int getItemsCount();

	public String getItem(int index);

	public int getMaximumLength();
}
