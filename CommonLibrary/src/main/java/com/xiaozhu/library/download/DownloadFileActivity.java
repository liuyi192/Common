package com.xiaozhu.library.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaozhu.library.R;
import com.xiaozhu.library.activity.BaseActivity;
import com.xiaozhu.library.entity.EventBusEntity;
import com.xiaozhu.library.entity.FileInfoEntity;
import com.xiaozhu.library.file.FileManagerUtils;
import com.xiaozhu.library.utils.ActivityManger;
import com.xiaozhu.library.utils.AppUtils;
import com.xiaozhu.library.utils.LogUtil;
import com.xiaozhu.library.utils.OpenFileUtil;
import com.xiaozhu.library.widget.custom.ColorArcProgressBar;
import com.xiaozhu.library.widget.custom.LineProgressBar;

import java.util.Random;

/**
 * @说明 文件下载
 * @作者 LY
 * @时间 2017/12/19 11:30
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2017 LY-版权所有
 * @备注
 */
public class DownloadFileActivity extends BaseActivity {
    private static final String DOWNLOAD_URL = "downloadUrl";
    private static final String DOWNLOAD_FILE_TYPE = "downloadFileType";
    private static final String FINISH_ALL = "finishAll";
    private static final String BACKGROUND_INSTALL = "backgroundInstall";
    private static final String LOADING_ANIM = "loadingAnim";

    private LineProgressBar progress;
    private ImageView loadingAnim;
    private TextView tvDownload;
    private ColorArcProgressBar colorArcProgress;

    /**
     * 跳转到下载
     *
     * @param context
     * @param downloadUrl 下载地址
     */
    public static void startDownload(Context context, String downloadUrl, int fileType) {
        startDownload(context, downloadUrl, fileType, false);
    }


    /**
     * 跳转到下载
     *
     * @param context
     * @param downloadUrl 下载地址
     */
    public static void startDownloadNotAnim(Context context, String downloadUrl, int fileType) {
        startDownload(context, downloadUrl, fileType, false, false);
    }

    /**
     * 跳转到下载
     *
     * @param context
     * @param downloadUrl 下载地址
     * @param finishAll   是否关闭全部界面
     */
    public static void startDownload(Context context, String downloadUrl, int fileType, boolean finishAll) {
        startDownload(context, downloadUrl, false, fileType, finishAll, true);
    }

    /**
     * 跳转到下载
     *
     * @param context
     * @param downloadUrl 下载地址
     * @param finishAll   是否关闭全部界面
     */
    public static void startDownload(Context context, String downloadUrl, int fileType, boolean finishAll, boolean loadingAnim) {
        startDownload(context, downloadUrl, false, fileType, finishAll, loadingAnim);
    }

    /**
     * 跳转到下载
     *
     * @param context
     * @param downloadUrl 下载地址
     * @param finishAll   是否关闭全部界面
     */
    public static void startDownload(Context context, String downloadUrl, boolean backgroundInstall, int fileType, boolean finishAll, boolean loadingAnim) {
        Intent intent = new Intent(context, DownloadFileActivity.class);
        intent.putExtra(DOWNLOAD_URL, downloadUrl);
        intent.putExtra(FINISH_ALL, finishAll);
        intent.putExtra(DOWNLOAD_FILE_TYPE, fileType);
        intent.putExtra(BACKGROUND_INSTALL, backgroundInstall);
        intent.putExtra(LOADING_ANIM, loadingAnim);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        loadingAnim = this.findViewById(R.id.loadingAnim);
        progress = this.findViewById(R.id.progress);
        tvDownload = this.findViewById(R.id.tvDownload);
        colorArcProgress = this.findViewById(R.id.colorArcProgress);
        progress.setMaxProgress(100);
        colorArcProgress.setMax(100);
        if (loadingAnim != null && loadingAnim.getBackground() instanceof AnimationDrawable) {
            AnimationDrawable animation = (AnimationDrawable) loadingAnim.getBackground();
            animation.start();
        }
        if (getIntent().getBooleanExtra(LOADING_ANIM, true)) {
            colorArcProgress.setVisibility(View.GONE);
            loadingAnim.setVisibility(View.VISIBLE);
            progress.setVisibility(View.VISIBLE);
        } else {
            colorArcProgress.setVisibility(View.VISIBLE);
            loadingAnim.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void business() {
        initRegister();
        String url = getIntent().getStringExtra(DOWNLOAD_URL);
        FileInfoEntity info = new FileInfoEntity(0, url, FileManagerUtils.getInstance().getFileName(url), 0, 0);
        Intent intent = new Intent(this, DownloadService.class);
        intent.setAction(DownloadService.ACTION_START);
        intent.putExtra("fileinfo", info);
        startService(intent);
    }

    /**
     * 注册广播接收器
     */
    private void initRegister() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadService.ACTION_UPDATE);
        filter.addAction(DownloadService.ACTION_FINISHED);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_download_file;
    }

    @Override
    public void onAsyncEventBus(final EventBusEntity eventBusEntity) {
        super.onAsyncEventBus(eventBusEntity);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 更新UI的广播接收器
     */
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadService.ACTION_UPDATE.equals(intent.getAction())) {
                int finished = intent.getIntExtra("finished", 0);
                progress.setCurProgress(finished);
                colorArcProgress.setProgress(finished);
                if (finished == 100) {
                    tvDownload.setText(R.string.download_complete);
                    String path = intent.getStringExtra("filePath");
                    if (getIntent().getBooleanExtra(FINISH_ALL, false)) {
                        ActivityManger.getInstance().finishAllActivity();
                    }
                    if (getIntent().getIntExtra(DOWNLOAD_FILE_TYPE, FileType.OFFICE.ordinal()) == FileType.APK.ordinal()) {
                        if (getIntent().getBooleanExtra(BACKGROUND_INSTALL, false)) {
                            AppUtils.execRootCmdSilent(path);
                        } else {
                            startActivity(OpenFileUtil.getApkFileIntent(path));
                        }
                    }
                    finish();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
        stopService(new Intent(this, DownloadService.class));
    }
}
