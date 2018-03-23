package com.xiaozhu.library.db.dao;

import android.content.Context;
import android.util.Log;

import com.xiaozhu.library.entity.CountEntity;
import com.xiaozhu.library.utils.LogUtil;
import com.xiaozhu.ormlite.OrmLiteDao;

import java.util.List;
import java.util.Map;

/**
 * @说明
 * @作者 LY
 * @时间 2018/2/2 10:46
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class CountDAOImpl implements CountDAO {
    private static CountDAOImpl mInstance;
    private static Context mContext;
    private OrmLiteDao dao;

    public CountDAOImpl(Context context) {
        dao = new OrmLiteDao(context, CountEntity.class);
    }

    public static CountDAOImpl getInstance(final Context context) {
        mContext = context;
        if (mInstance == null) {
            synchronized (CountDAOImpl.class) {
                if (mInstance == null) {
                    mInstance = new CountDAOImpl(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void saveStartData(CountEntity countEntity) {
        CountEntity countData = findDataByClassId(countEntity.getClassId());
        if (countData != null) {
            countData.setStartTime(countEntity.getStartTime());
            countData.setStartCountData(countEntity.getStartCountData());
            dao.update(countData);
        } else {
            dao.insert(countEntity);
        }
    }

    @Override
    public void saveEndData(CountEntity countEntity) {
        CountEntity countData = findDataByClassId(countEntity.getClassId());
        if (countData != null) {
            countData.setEndCountData(countEntity.getEndCountData());
            countData.setEndTime(countEntity.getEndTime());
            dao.update(countData);
        } else {
            dao.insert(countEntity);
        }
    }

    @Override
    public CountEntity findDataByClassId(String classId) {
        return (CountEntity) dao.queryForFirst("classId", classId);
    }

    @Override
    public void updateUploadType(String classId) {
        CountEntity countData = findDataByClassId(classId);
        if (countData != null) {
            countData.setCountType(1);
            dao.update(countData);
        }
    }

    @Override
    public List<CountEntity> findAllNoUpload() {
        return dao.queryByColumnName("countType", 0);
    }
}
