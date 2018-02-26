package com.xiaozhu.library.db.dao;

import com.xiaozhu.library.entity.CountEntity;

import java.util.List;

/**
 * @说明 数据统计数据
 * @作者 LY
 * @时间 2018/2/2 10:43
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public interface CountDAO {
    void saveStartData(CountEntity countEntity);

    void saveEndData(CountEntity countEntity);

    CountEntity findDataByClassId(String classId);

    void updateUploadType(String classId);

    List<CountEntity> findAllNoUpload();
}
