package com.xiaozhu.library.count;

import android.content.Context;

import com.xiaozhu.library.db.dao.CountDAOImpl;
import com.xiaozhu.library.entity.CountEntity;
import com.xiaozhu.library.utils.GsonUtils;
import com.xiaozhu.library.utils.TimeUtils;

import java.util.List;
import java.util.Map;

/**
 * @说明 数据统计工具类
 * @作者 LY
 * @时间 2018/2/2 10:30
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class CountUtils {

    /**
     * 保存进入界面数据
     *
     * @param context
     * @param classId
     * @param map
     */
    public static void start(Context context, String classId, Map<String, Object> map) {
        CountEntity countEntity = new CountEntity();
        countEntity.setClassId(classId);
        countEntity.setCreateTime(TimeUtils.toDay());
        countEntity.setStartCountData(GsonUtils.beanToJson(map));
        CountDAOImpl.getInstance(context).saveStartData(countEntity);
    }

    /**
     * 保存退出界面数据
     *
     * @param context
     * @param classId
     * @param map
     */
    public static void end(Context context, String classId, Map<String, Object> map) {
        CountEntity countEntity = new CountEntity();
        countEntity.setClassId(classId);
        countEntity.setCreateTime(TimeUtils.toDay());
        countEntity.setEndCountData(GsonUtils.beanToJson(map));
        CountDAOImpl.getInstance(context).saveEndData(countEntity);
    }

    /**
     * 查询所有未上传的数据
     *
     * @param context
     * @return
     */
    public static List<CountEntity> findAll(Context context) {
        return CountDAOImpl.getInstance(context).findAllNoUpload();
    }

    /**
     * 修改上传
     *
     * @param context
     * @param classId
     */
    public static void updateUploadType(Context context, String classId) {
        CountDAOImpl.getInstance(context).updateUploadType(classId);
    }

}
