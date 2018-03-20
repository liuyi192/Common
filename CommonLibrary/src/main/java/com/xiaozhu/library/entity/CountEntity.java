package com.xiaozhu.library.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @说明
 * @作者 LY
 * @时间 2018/2/2 10:36
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
@DatabaseTable(tableName = "tab_count")
public class CountEntity extends BaseEntity {
    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(columnName = "classId")
    private String classId;// 类编号
    @DatabaseField(columnName = "startCountData")
    private String startCountData;//开始数据
    @DatabaseField(columnName = "endCountData")
    private String endCountData;//结束统计数据
    @DatabaseField(columnName = "createTime")
    private String createTime;//创建时间
    @DatabaseField(columnName = "countType")
    private int countType;// 0.未上传 1.已上传
    @DatabaseField(columnName = "startTime")
    private long startTime;
    @DatabaseField(columnName = "endTime")
    private long endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStartCountData() {
        return startCountData;
    }

    public void setStartCountData(String startCountData) {
        this.startCountData = startCountData;
    }

    public String getEndCountData() {
        return endCountData;
    }

    public void setEndCountData(String endCountData) {
        this.endCountData = endCountData;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCountType() {
        return countType;
    }

    public void setCountType(int countType) {
        this.countType = countType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "CountEntity{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", startCountData='" + startCountData + '\'' +
                ", endCountData='" + endCountData + '\'' +
                ", createTime='" + createTime + '\'' +
                ", countType=" + countType +
                '}';
    }
}
