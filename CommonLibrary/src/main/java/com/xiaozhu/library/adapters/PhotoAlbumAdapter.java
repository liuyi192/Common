package com.xiaozhu.library.adapters;

import android.content.Context;
import android.view.View;

import com.xiaozhu.library.R;
import com.xiaozhu.library.entity.ImgListEntity;

/**
 * @说明 相册
 * @作者 LY
 * @时间 2018/1/29 10:41
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class PhotoAlbumAdapter extends BaseRecyclerAdapter<PhotoAlbumViewHolder, ImgListEntity> {
    public PhotoAlbumAdapter(Context context) {
        super(context);
    }

    @Override
    public PhotoAlbumViewHolder createView(View convertView, int viewType) {
        return new PhotoAlbumViewHolder(convertView, viewType);
    }

    @Override
    public void bindData(PhotoAlbumViewHolder holder, ImgListEntity imgListEntity, int position) {
        if (imgListEntity != null) {
            holder.image.loadImage(imgListEntity.getImgUrl(), R.mipmap.img_default);
            if (imgListEntity.isSelect()) {
                holder.btnSelect.setVisibility(View.VISIBLE);
            } else {
                holder.btnSelect.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getLayoutResID(int viewType) {
        return R.layout.common_photo_album_item;
    }
}
