package com.xiaozhu.library.widget.wheel;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xiaozhu.library.R;

import java.util.List;

/**
 * @说明
 * @作者 LY
 * @文件 ArrayWheelAdapter.java
 * @时间 2015年6月19日 下午2:14:24
 * @版权 Copyright(c) 2014 LY-版权所有
 */
public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {
    private List<CodeItem> arrayList = null;
    private boolean showShi = true;
    private Context mContext;

    public boolean isShowShi() {
        return showShi;
    }

    public void setShowShi(boolean showShi) {
        this.showShi = showShi;
        notifyDataChangedEvent();
    }

    /**
     * Constructor
     *
     * @param context the current context
     * @param items   the items
     */
    public ArrayWheelAdapter(Context context, List<CodeItem> items, int itemResource, int itemTextResource) {
        super(context, itemResource, itemTextResource);
        this.arrayList = items;
        this.mContext = context;
    }

    @Override
    public CodeItem getItemText(int index) {
        if (index >= 0 && index < arrayList.size()) {
            return arrayList.get(index);
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return arrayList.size();
    }

    @Override
    protected void buildView(View view, int index) {
        TextView textView = (TextView) view.findViewById(itemTextResourceId);
        CodeItem text = getItemText(index);
        textView.setText(text.getTitle());
        if (text.getChooseStatus() == 0) {
            textView.setTextColor(mContext.getResources().getColor(R.color.gray));
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.black));
        }
    }
}
