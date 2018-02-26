package com.xiaozhu.library.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaozhu.library.R;
import com.xiaozhu.library.adapters.BaseRecyclerAdapter;
import com.xiaozhu.library.adapters.PhotoAlbumAdapter;
import com.xiaozhu.library.entity.ImgListEntity;
import com.xiaozhu.library.eventBus.EventBusUtils;
import com.xiaozhu.library.mvp.PhotoAlbumPresenter;
import com.xiaozhu.library.mvp.PhotoAlbumView;
import com.xiaozhu.library.widget.custom.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明
 * @作者 LY
 * @时间 2018/1/29 10:40
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class PhotoAlbumActivity extends BaseRecyclerActivity implements PhotoAlbumView {
    private static final String MAX_NUM = "maxNum";
    private static final String SELECT_TYPE = "selectType";
    private static final String MULTI_SELECT = "multiSelect";
    private PhotoAlbumPresenter photoAlbumPresenter;
    private int maxNum = 16;

    /**
     * 选择多图
     *
     * @param context
     * @param maxNum
     * @param selectType
     */
    public static void startPhotoAlbum(Context context, int maxNum, int selectType) {
        Intent intent = new Intent(context, PhotoAlbumActivity.class);
        intent.putExtra(MAX_NUM, maxNum);
        intent.putExtra(SELECT_TYPE, selectType);
        intent.putExtra(MULTI_SELECT, true);
        context.startActivity(intent);
    }

    /**
     * 多选相册
     *
     * @param context
     * @param selectType
     */
    public static void startMultiSelect(Context context, int selectType) {
        Intent intent = new Intent(context, PhotoAlbumActivity.class);
        intent.putExtra(SELECT_TYPE, selectType);
        intent.putExtra(MULTI_SELECT, false);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        photoAlbumPresenter = new PhotoAlbumPresenter(this);
        maxNum = getIntent().getIntExtra(MAX_NUM, 16);
        titleBar.setTitle(R.string.photo_album);
        titleBar.setBtnLeft(R.mipmap.icon_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent().getBooleanExtra(MULTI_SELECT, false)) {
            titleBar.setBtnTvRight(getResources().getString(R.string.confirm_num, String.valueOf(0), String.valueOf(maxNum)), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBusUtils.getInstance().sendEventBus(getIntent().getIntExtra(SELECT_TYPE, 1000), selectNum());
                    finish();
                }
            });
        }
    }

    @Override
    public void business() {
        startRefresh();
    }

    @Override
    public void loadingData() {
        loadingView.loading();
        photoAlbumPresenter.findAllImg(this);
    }

    @Override
    public void itemClick(Object o, int position) {
        if (getIntent().getBooleanExtra(MULTI_SELECT, false)) {
            if (selectNum().size() < maxNum) {
                ((PhotoAlbumAdapter) adapter).getItem(position).setSelect(!((PhotoAlbumAdapter) adapter).getItem(position).isSelect());
                adapter.notifyDataSetChanged();
                titleBar.setBtnTvRightName(getResources().getString(R.string.confirm_num, String.valueOf(selectNum().size()), String.valueOf(maxNum)));
            } else {
                ToastUtils.showToast(getResources().getString(R.string.select_photo_max));
            }
        } else {
            ((PhotoAlbumAdapter) adapter).getItem(position).setSelect(!((PhotoAlbumAdapter) adapter).getItem(position).isSelect());
            adapter.notifyDataSetChanged();
            EventBusUtils.getInstance().sendEventBus(getIntent().getIntExtra(SELECT_TYPE, 1000), selectNum());
            finish();
        }
    }

    @Override
    public void findAllSuccess(List<ImgListEntity> list) {
        if (list != null && list.size() > 0) {
            adapter.setList(list);
            loadingView.setVisibility(View.GONE);
        } else {
            loadingView.setVisibility(View.VISIBLE);
            loadingView.loadingDataEmpty();
        }
        stopRefresh();
    }

    @Override
    public void findAllFailed() {
        loadingView.setVisibility(View.VISIBLE);
        loadingView.loadingDataEmpty();
        stopRefresh();
    }

    @Override
    public BaseRecyclerAdapter getAdapter() {
        return new PhotoAlbumAdapter(this);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3);
    }

    public List<ImgListEntity> selectNum() {
        List<ImgListEntity> list = new ArrayList<>();
        for (ImgListEntity imgList : ((PhotoAlbumAdapter) adapter).getListData()) {
            if (imgList.isSelect()) {
                list.add(imgList);
            }
        }
        return list;
    }
}
