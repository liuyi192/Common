package com.xiaozhu.library.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @说明 下载对象
 * @作者 LY
 * @时间 2017/12/26 11:35
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
@DatabaseTable(tableName = "thread_info")
public class DownloadEntity extends BaseEntity {
    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(columnName = "thread_id")
    private int threadId;
    @DatabaseField(columnName = "url")
    private String url;
    @DatabaseField(columnName = "start")
    private long start;
    @DatabaseField(columnName = "end")
    private long end;
    @DatabaseField(columnName = "finished")
    private long finished;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
