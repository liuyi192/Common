package com.xiaozhu.library.adapters;

import android.view.View;
import android.widget.ImageView;

import com.xiaozhu.library.R;
import com.xiaozhu.library.widget.glideImage.GlideImageView;

/**
 * @说明
 * @作者 LY
 * @时间 2018/1/29 10:41
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class PhotoAlbumViewHolder extends BaseRecyclerViewHolder {
    public GlideImageView image;
    public ImageView btnSelect;

    public PhotoAlbumViewHolder(View itemView, int viewType) {
        super(itemView, viewType);
        image = itemView.findViewById(R.id.image);
        btnSelect = itemView.findViewById(R.id.btnSelect);
    }
}
