package com.xiaozhu.library.file;

/**
 * @说明 文件管理类公共方法
 * @作者 LY
 * @时间 2016/9/22 10:15
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2016 LY-版权所有
 * @备注
 */
public interface FileManagerInterface {
    /**
     * 图片地址
     */
    String IMG_FOLDER = "/img/";
    /**
     * 日志地址
     */
    String LOG_FOLDER = "/log/";
    /**
     * 音频
     */
    String AUDIO_FOLDER = "/audio/";
    /**
     * APK文件
     */
    String APK_FOLDER = "/apk/";
    /**
     * 文件
     */
    String TXT_FOLDER = "/txt/";
    /**
     * 压缩图片
     */
    String COMPRESS_FOLDER = "/compress/";
    /**
     * 下載路徑
     */
    String DOWNLOAD_FOLDER = "/download/";

    /**
     * 获取压缩图片路径
     */
    String getCompressFolder();

    /**
     * 设置文件夹路径
     *
     * @param name 文件名称
     */
    void setFolderName(String name);

    /**
     * 获取APK路径
     */
    String getApkFolder();

    /**
     * 获取日志路径
     */
    String getLogFolder();

    /**
     * 获取图片路径
     */
    String getImgFolder();

    /**
     * 获取音频路径
     */
    String getAudioFolder();

    /**
     * 获取文本路径
     */
    String getTxtFolder();
    /**
     * 获取下載路径
     */
    String getDownloadFolder();

    /**
     * 判断是否有存储卡
     *
     * @return 存在/不存在
     */
    boolean isSdcard();

    /**
     * 获取存储卡路径
     *
     * @return
     */
    String getSdcardPath();

    /**
     * 获取App文件夹路径
     *
     * @return APP路径
     */
    String getAppPath();


    /**
     * 获取文件大小
     *
     * @param filePath 文件路径
     * @return 文件大小
     */
    long getFileSize(String filePath);

    /**
     * 获取本地文件夹大小
     *
     * @return 文件大小
     */
    String getFileSize();

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹路径
     */
    void delFolder(String folderPath);

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 路径
     * @return 是否成功
     */
    boolean delAllFile(String path);
}
