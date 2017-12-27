package com.xiaozhu.library.download;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaozhu.library.entity.FileInfoEntity;
import com.xiaozhu.library.file.FileManagerUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @说明 下载服务
 * @作者 LY
 * @时间 2017/12/26 10:46
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class DownloadService extends Service {
    //初始化
    private static final int MSG_INIT = 0;
    //开始下载
    public static final String ACTION_START = "ACTION_START";
    //暂停下载
    public static final String ACTION_PAUSE = "ACTION_PAUSE";
    //结束下载
    public static final String ACTION_FINISHED = "ACTION_FINISHED";
    //更新UI
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    //下载路径
    public static final String DOWNLOAD_PATH = FileManagerUtils.getInstance().getDownloadFolder();

    private DownloadTask mDownloadTask = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //获得Activity传来的参数
        if (ACTION_START.equals(intent.getAction())) {
            FileInfoEntity fileInfo = (FileInfoEntity) intent.getSerializableExtra("fileinfo");
            new InitThread(fileInfo).start();
        } else if (ACTION_PAUSE.equals(intent.getAction())) {
            FileInfoEntity fileInfo = (FileInfoEntity) intent.getSerializableExtra("fileinfo");
            if (mDownloadTask != null) {
                mDownloadTask.isPause = true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT:
                    FileInfoEntity fileinfo = (FileInfoEntity) msg.obj;
                    //启动下载任务
                    mDownloadTask = new DownloadTask(DownloadService.this, fileinfo);
                    mDownloadTask.download();
                    break;
            }
        }
    };

    /**
     * 初始化 子线程
     */
    class InitThread extends Thread {
        private FileInfoEntity tFileInfo;
        public InitThread(FileInfoEntity tFileInfo) {
            this.tFileInfo = tFileInfo;
        }
        @Override
        public void run() {
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            try {
                //连接网络文件
                URL url = new URL(tFileInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                int length = -1;
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //获取文件长度
                    length = conn.getContentLength();
                }
                if (length < 0) {
                    return;
                }
                File dir = new File(DOWNLOAD_PATH);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                //在本地创建文件
                File file = new File(dir, tFileInfo.getFileName());
                raf = new RandomAccessFile(file, "rwd");
                //设置本地文件长度
                raf.setLength(length);
                tFileInfo.setLength(length);
                mHandler.obtainMessage(MSG_INIT, tFileInfo).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null && raf != null) {
                        raf.close();
                        conn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
