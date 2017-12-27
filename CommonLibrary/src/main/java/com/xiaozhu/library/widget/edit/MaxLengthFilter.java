package com.xiaozhu.library.widget.edit;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;

import com.xiaozhu.library.R;
import com.xiaozhu.library.widget.custom.ToastUtils;

/**
 * @说明 长度限制
 * @作者 LY
 * @时间 2017/11/22 14:21
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class MaxLengthFilter implements InputFilter {
    private final int mMax;
    private Context context;

    public MaxLengthFilter(int max, Context context) {
        mMax = max;
        this.context = context;
    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int keep = mMax - (dest.length() - (dend - dstart));
        if (keep <= 0) {
            ToastUtils.showToast(context.getResources().getString(R.string.msg_num_length));
            return "";
        } else if (keep >= end - start) {
            return null;
        } else {
            keep += start;
            if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                --keep;
                if (keep == start) {
                    return "";
                }
            }
            return source.subSequence(start, keep);
        }
    }

    public int getMax() {
        return mMax;
    }
}
