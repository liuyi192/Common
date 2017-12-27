package com.xiaozhu.library.db.dao;

import com.xiaozhu.library.entity.ThreadInfoEntity;

import java.util.List;

/**
 * @说明
 * @作者 LY
 * @时间 2017/12/26 11:39
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface ThreadDAO {
    /**
     * 插入线程信息
     *
     * @param threadInfo 线程信息
     */
    void insertThread(ThreadInfoEntity threadInfo);

    /**
     * 删除线程信息
     *
     * @param url 地址
     */
    void deleteThread(String url,int id);


    /**
     * /**
     * 更新线程信息
     *
     * @param url       地址
     * @param thread_id id
     * @param finished  完成进度
     */
    void updateThread(String url, int thread_id, long finished);


    /**
     * 查询文件的线程信息
     *
     * @param url 地址
     * @return 信息
     */
    List<ThreadInfoEntity> getThread(String url);


    /**
     * 判断是否存在
     *
     * @param url       地址
     * @param thread_id id
     * @return 是否存在
     */
    boolean isExists(String url, int thread_id);
}
