package com.xiaozhu.library.mvp;


import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.provider.MediaStore;

import com.xiaozhu.library.entity.ImgListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @说明 图集
 * @作者 LY
 * @时间 2018/1/29 14:11
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class PhotoAlbumPresenter extends BasePresenter {
    private final String[] IMAGE_PROJECTION = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media._ID};
    private PhotoAlbumView photoAlbumView;

    public PhotoAlbumPresenter(PhotoAlbumView photoAlbumView) {
        super(photoAlbumView);
        this.photoAlbumView = photoAlbumView;
    }

    /**
     * 查询所有图片
     */
    public void findAllImg(Context context) {
        List<ImgListEntity> list = new ArrayList<>();
        Cursor cursor = MediaStore.Images.Media.query(context.getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION, null, MediaStore.Images.Media.DATE_TAKEN + " DESC");
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
            list.add(new ImgListEntity(path));
        }
        cursor.close();
        photoAlbumView.findAllSuccess(list);
    }

}
