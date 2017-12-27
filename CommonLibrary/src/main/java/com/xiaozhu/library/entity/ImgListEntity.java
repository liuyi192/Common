package com.xiaozhu.library.entity;

/**
 * @说明 图片对象
 * @作者 LY
 * @时间 2017/11/8 20:23
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class ImgListEntity extends BaseEntity {
    private String ids;
    private String imgUrl;
    private boolean select;

    public ImgListEntity() {
    }

    public ImgListEntity(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
