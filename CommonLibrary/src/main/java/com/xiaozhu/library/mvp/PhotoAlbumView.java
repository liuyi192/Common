package com.xiaozhu.library.mvp;

import com.xiaozhu.library.entity.ImgListEntity;

import java.util.List;

/**
 * @说明 相册
 * @作者 LY
 * @时间 2018/1/29 14:03
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public interface PhotoAlbumView extends BaseView {
    void findAllSuccess(List<ImgListEntity> list);

    void findAllFailed();
}
