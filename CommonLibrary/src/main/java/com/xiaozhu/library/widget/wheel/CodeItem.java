package com.xiaozhu.library.widget.wheel;

/**
 * @说明 数据对象
 * @作者 LY
 * @文件 CodeItem.java
 * @时间 2015年6月19日 下午2:16:46
 * @版权 Copyright(c) 2014 LY-版权所有
 */
public class CodeItem {
    private String title = "";
    private int chooseStatus = 0;
    private int type;
    private String ids;

    private String p_code;
    private String c_code;
    private String a_code;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChooseStatus() {
        return chooseStatus;
    }

    public void setChooseStatus(int chooseStatus) {
        this.chooseStatus = chooseStatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getP_code() {
        return p_code;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getA_code() {
        return a_code;
    }

    public void setA_code(String a_code) {
        this.a_code = a_code;
    }
}
