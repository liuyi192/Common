package com.xiaozhu.library.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @说明 键盘工具类
 * @作者 LY
 * @时间 2016/9/26 13:38
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2016 LY-版权所有
 * @备注
 */
public class KeyBoardUtils {
    private static KeyBoardUtils instance;

    public static KeyBoardUtils getInstance() {
        if (instance == null) {
            synchronized (KeyBoardUtils.class) {
                if (instance == null) {
                    instance = new KeyBoardUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 打开输入键盘
     *
     * @param context
     * @param editText 输入框
     */
    public void openKeyBoard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    /**
     * 关闭输入键盘
     *
     * @param context
     * @param editText 输入框
     */
    public void closeKeyBord(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
