package com.xiaozhu.library.db.dao;

import android.content.Context;

import com.xiaozhu.library.entity.DownloadEntity;
import com.xiaozhu.library.entity.ThreadInfoEntity;
import com.xiaozhu.ormlite.OrmLiteDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @说明 线程实现
 * @作者 LY
 * @时间 2017/12/26 11:43
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class ThreadDAOImpl implements ThreadDAO {
    private static ThreadDAOImpl mInstance;
    private static Context mContext;
    private OrmLiteDao dao;

    public ThreadDAOImpl(Context context) {
        dao = new OrmLiteDao(context, DownloadEntity.class);
    }

    public static ThreadDAOImpl getInstance(final Context context) {
        mContext = context;
        if (mInstance == null) {
            synchronized (ThreadDAOImpl.class) {
                if (mInstance == null) {
                    mInstance = new ThreadDAOImpl(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void insertThread(ThreadInfoEntity threadInfo) {
        DownloadEntity downloadEntity = new DownloadEntity();
        downloadEntity.setThreadId(threadInfo.getId());
        downloadEntity.setStart(threadInfo.getStart());
        downloadEntity.setEnd(threadInfo.getEnd());
        downloadEntity.setUrl(threadInfo.getUrl());
        downloadEntity.setFinished(threadInfo.getFinish());
        dao.insert(downloadEntity);
    }

    @Override
    public void deleteThread(String url,int id) {
        Map<String,Object> map = new HashMap<>();
        map.put("url",url);
        map.put("thread_id",id);
        dao.deleteByColumnName(map);
    }

    @Override
    public void updateThread(String url, int thread_id, long finished) {
        Map<String,Object> map = new HashMap<>();
        map.put("url",url);
        map.put("thread_id",thread_id);
       DownloadEntity downloadEntity = (DownloadEntity) dao.queryForFirst(map);
       if (downloadEntity!=null){
           downloadEntity.setFinished(finished);
           dao.update(downloadEntity);
       }
    }

    @Override
    public List<ThreadInfoEntity> getThread(String url) {
        List<DownloadEntity> list = dao.queryLikeList("url",url);
        List<ThreadInfoEntity> listThread = new ArrayList<>();
        if (list!=null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                DownloadEntity downloadEntity = list.get(i);
                listThread.add(new ThreadInfoEntity(
                        downloadEntity.getThreadId(),
                        downloadEntity.getUrl(),
                        downloadEntity.getStart(),
                        downloadEntity.getEnd(),
                        downloadEntity.getFinished()));
            }
        }
        return listThread;
    }

    @Override
    public boolean isExists(String url, int thread_id) {
        Map<String,Object> map = new HashMap<>();
        map.put("url",url);
        map.put("thread_id",thread_id);
        DownloadEntity downloadEntity = (DownloadEntity) dao.queryForFirst(map);
        if (downloadEntity!=null){
            return true;
        }
        return false;
    }
}
