package com.xiaozhu.common.banner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaozhu.common.R;
import com.xiaozhu.library.entity.BaseEntity;
import com.xiaozhu.library.widget.glideImage.GlideImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明
 * @作者 LY
 * @时间 2017/12/27 13:51
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class BannerAdapter extends PagerAdapter {
    private List<BannerEntity> list = new ArrayList<>();
    private Context mContext;

    public BannerAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(BannerEntity baseEntity) {
        list.add(baseEntity);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.common_baner_item, container, false);
        GlideImageView imageView = (GlideImageView) view.findViewById(R.id.images);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        BannerEntity bannerEntity = list.get(position);
        if (bannerEntity != null) {
            imageView.loadImage(bannerEntity.getImageUrl(), R.mipmap.icon_error);
            tvTitle.setText(bannerEntity.getTitle());
        }
        container.addView(view);
        return view;
    }

}
