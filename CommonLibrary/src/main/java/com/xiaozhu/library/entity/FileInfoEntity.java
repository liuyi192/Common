package com.xiaozhu.library.entity;

import java.io.Serializable;

/**
 * @说明 文件实体类
 * @作者 LY
 * @时间 2017/12/26 10:46
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class FileInfoEntity implements Serializable {
    private  int id;
    private String url;
    private String fileName;
    private long length;
    private long finish;

    public FileInfoEntity() {
    }

    public FileInfoEntity(int id, String url, String fileName, long length, long finish) {
        this.id = id;
        this.url = url;
        this.fileName = fileName;
        this.length = length;
        this.finish = finish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getFinish() {
        return finish;
    }

    public void setFinish(long finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FileInfo{");
        sb.append("id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", length=").append(length);
        sb.append(", finish=").append(finish);
        sb.append('}');
        return sb.toString();
    }
}
