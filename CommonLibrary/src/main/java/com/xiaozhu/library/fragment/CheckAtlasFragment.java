package com.xiaozhu.library.fragment;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.xiaozhu.library.R;

import uk.co.senab.photoview.PhotoView;

/**
 * @说明 图集Fragment
 * @作者 LY
 * @时间 2018/1/25 15:49
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class CheckAtlasFragment extends BaseFragment {
    private static final String IMG_URL = "imgUrl";
    private PhotoView photoView;

    public static CheckAtlasFragment newInstance(String imgUrl) {
        CheckAtlasFragment checkAtlasFragment = new CheckAtlasFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMG_URL, imgUrl);
        checkAtlasFragment.setArguments(bundle);
        return checkAtlasFragment;
    }

    @Override
    public void initView(View view) {
        photoView = view.findViewById(R.id.photoView);
    }

    @Override
    public void business() {
        String imgUrl = getArguments().getString(IMG_URL);
        Glide.with(getActivity()).load(imgUrl).into(photoView);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_check_atlas;
    }
}
