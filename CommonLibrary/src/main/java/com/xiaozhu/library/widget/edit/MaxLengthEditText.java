package com.xiaozhu.library.widget.edit;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.util.AttributeSet;

/**
 * @说明 输入框文字长度
 * @作者 LY
 * @时间 2017/11/22 14:20
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class MaxLengthEditText extends AppCompatEditText {
    private static String MAX_LENGTH = "maxLength";

    public MaxLengthEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLength(attrs, context);
    }

    public MaxLengthEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLength(attrs, context);
    }

    private void initLength(AttributeSet a, Context context) {
        String namespace = "http://schemas.android.com/apk/res/android";
        int maxLength = a.getAttributeIntValue(namespace, MAX_LENGTH, -1);
        if (maxLength > -1) {
            setFilters(new InputFilter[]{new MaxLengthFilter(maxLength, context)});
        }
    }
}
