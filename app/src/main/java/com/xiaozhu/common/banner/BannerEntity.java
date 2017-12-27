package com.xiaozhu.common.banner;

import com.xiaozhu.library.entity.BaseEntity;

/**
 * @说明
 * @作者 LY
 * @时间 2017/12/27 14:36
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class BannerEntity extends BaseEntity {
    private String imageUrl;
    private String title;

    public BannerEntity(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
