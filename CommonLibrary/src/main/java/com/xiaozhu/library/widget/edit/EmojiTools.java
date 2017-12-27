package com.xiaozhu.library.widget.edit;

import android.text.TextUtils;

/**
 * @说明 表情工具
 * @作者 LY
 * @时间 2017/7/30 下午12:06
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class EmojiTools {
    /**
     * 是否包含Emoji表情
     *
     * @param str
     * @return
     */
    public static boolean containsEmoji(String str) {
        if (TextUtils.isEmpty(str)) return false;
        for (int i = 0; i < str.length(); i++) {
            int cp = str.codePointAt(i);
            if (isEmojiCharacter(cp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查是否存在Emoji表情
     *
     * @param first
     * @return
     */
    private static boolean isEmojiCharacter(int first) {
        return !((first == 0x0) || (first == 0x9) || (first == 0xA) || (first == 0xD) || ((first >= 0x20) && (first <= 0xD7FF)) || ((first >= 0xE000) && (first <= 0xFFFD)) || ((first >= 0x10000))) || (first == 0xa9 || first == 0xae || first == 0x2122 || first == 0x3030 || (first >= 0x25b6 && first <= 0x27bf) || first == 0x2328 || (first >= 0x23e9 && first <= 0x23fa)) || ((first >= 0x1F000 && first <= 0x1FFFF)) || ((first >= 0x2702) && (first <= 0x27B0)) || ((first >= 0x1F601) && (first <= 0x1F64F));
    }

    /**
     * 过滤Emoji表情
     *
     * @param str 需要过滤的
     * @return 已经过滤的
     */
    public static String filterEmoji(String str) {
        if (!containsEmoji(str)) {
            return str;
        }
        StringBuilder buf = null;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char codePoint = str.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(str.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return "";
        } else {
            return buf.toString();
        }
    }
}
