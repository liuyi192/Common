package com.xiaozhu.library.widget.wheel;

/**
 * @说明
 * @作者 LY
 * @文件 ItemsRange.java
 * @时间 2015年6月19日 下午1:45:31
 * @版权 Copyright(c) 2014 LY-版权所有
 */
public class ItemsRange {
    private int first;
    private int count;

    public ItemsRange() {
        this(0, 0);
    }

    public ItemsRange(int first, int count) {
        this.first = first;
        this.count = count;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return getFirst() + getCount() - 1;
    }

    public int getCount() {
        return count;
    }

    public boolean contains(int index) {
        return index >= getFirst() && index <= getLast();
    }
}