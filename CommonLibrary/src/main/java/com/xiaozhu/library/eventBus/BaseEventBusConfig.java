package com.xiaozhu.library.eventBus;

/**
 * @说明 基础EventBus配置
 * @作者 LY
 * @时间 2017/12/21 16:15
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public interface BaseEventBusConfig {
    //下载文件已经存在
    int DOWNLOAD_EXIST = 50001;
    //下载中
    int DOWNLOAD_LOADING = 50002;
    //下载失败
    int DOWNLOAD_ERROR = 50003;
    //下載完成
    int DOWNLOAD_COMPLETE = 50004;
}
