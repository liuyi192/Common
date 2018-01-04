package com.xiaozhu.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @说明 Preferences工具类
 * @作者 LY
 * @时间 2017/12/21 11:03
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class PreferencesUtils {

    static String SP_SETTING = "setting";

    /**
     * 保存字符串
     *
     * @param context
     * @param key     对应KEY
     * @param values  对应值
     */
    public static void saveString(Context context, String key, String values) {
        SharedPreferences sp = context.getSharedPreferences(SP_SETTING, 0);
        sp.edit().putString(key, values).commit();
    }

    /**
     * 获取字符串的值
     *
     * @param context
     * @param key     对应KEY
     * @return KEY对饮的值
     */
    public static String getStringValues(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_SETTING, 0);
        return sp.getString(key, "");
    }

    /**
     * 保存int数据
     *
     * @param context
     * @param key     对应KEY
     * @param values  对应值
     */
    public static void saveIntValues(Context context, String key, int values) {
        SharedPreferences sp = context.getSharedPreferences(SP_SETTING, 0);
        sp.edit().putInt(key, values).commit();
    }

    /**
     * 获取int类型数据
     *
     * @param context
     * @param key     对应KEY
     * @return KEY对饮的值
     */
    public static int getIntValues(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_SETTING, 0);
        return sp.getInt(key, 0);
    }

    /**
     * 保存boolean值
     *
     * @param context
     * @param key     对应KEY
     * @param values  对应值
     */
    public static void saveBooleanValues(Context context, String key, boolean values) {
        SharedPreferences sp = context.getSharedPreferences(SP_SETTING, 0);
        sp.edit().putBoolean(key, values).commit();
    }

    /**
     * 获取boolean值
     *
     * @param context
     * @param key     对应KEY
     * @return KEY对饮的值
     */
    public static boolean getBooleanValues(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_SETTING, 0);
        return sp.getBoolean(key, false);
    }

}
