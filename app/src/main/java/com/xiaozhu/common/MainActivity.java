package com.xiaozhu.common;

import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xiaozhu.common.banner.BannerAdapter;
import com.xiaozhu.common.banner.BannerEntity;
import com.xiaozhu.library.entity.BaseEntity;
import com.xiaozhu.library.widget.banner.LoopViewPager;
import com.xiaozhu.library.widget.custom.CircleIndicator;

import java.io.IOException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private CircleIndicator indicator;
    private LoopViewPager viewPager;
    private String images[] = {
            "http://mpic.tiankong.com/7a1/afd/7a1afd23a1586dccd5296ed8ccca99e1/640.jpg",
            "http://mpic.tiankong.com/35a/6db/35a6db1ea79162b3aff76d173be810b5/640.jpg",
            "http://mpic.tiankong.com/efd/9da/efd9dafaf6d8afc0b5bd21cfc3de0e7f/640.jpg",
            "http://mpic.tiankong.com/9ff/e39/9ffe397153519568a87ce45a083c0f8e/640.jpg",
            "http://mpic.tiankong.com/810/492/810492a631686acb6740f91b10d57f8c/640.jpg"
    };
    private String title[] = {
            "测试图片 都是",
            "测试图片 都是谁说的",
            "测试图片 时代大厦多收到",
            "测试图片 都是电视电话",
            "测试图片 房屋初始化"
    };

    SurfaceView surfaceview1, surfaceview2;
    SurfaceHolder surfaceholder1, surfaceholder2;
    private Camera camera1 = null, camera2;
    Camera.Parameters parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indicator = this.findViewById(R.id.indicator);
        viewPager = this.findViewById(R.id.viewPager);
        BannerAdapter bannerAdapter = new BannerAdapter(this);
        for (int i = 0; i < images.length; i++) {
            bannerAdapter.addData(new BannerEntity(images[i], title[i]));
        }
        viewPager.setAdapter(bannerAdapter);
        viewPager.setOnDispatchTouchEventListener(mDispatchOnTouchListener);
        viewPager.setLooperPic(true);

        indicator.setViewPager(viewPager);


        {
            surfaceview1 = (SurfaceView) findViewById(R.id.surfaceview1);
            surfaceview2 = (SurfaceView) findViewById(R.id.surfaceview2);
            surfaceholder1 = surfaceview1.getHolder();
            surfaceholder1.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            surfaceholder1.addCallback(new surfaceholderCallbackBack());

            surfaceholder2 = surfaceview2.getHolder();
            surfaceholder2.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            surfaceholder2.addCallback(new surfaceholderCallbackFont());
        }
    }

    class surfaceholderCallbackBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // 获取camera对象
            int cameraCount = Camera.getNumberOfCameras();
            if (cameraCount > 0) {
                camera1 = Camera.open(0);
                try {
                    // 设置预览监听
                    camera1.setPreviewDisplay(holder);
                    Camera.Parameters parameters = camera1.getParameters();

                    if (MainActivity.this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                        parameters.set("orientation", "portrait");
                        camera1.setDisplayOrientation(90);
                        parameters.setRotation(90);
                    } else {
                        parameters.set("orientation", "landscape");
                        camera1.setDisplayOrientation(0);
                        parameters.setRotation(0);
                    }
                    camera1.setParameters(parameters);
                    // 启动摄像头预览
                    camera1.startPreview();
                    System.out.println("camera.startpreview");

                } catch (IOException e) {
                    e.printStackTrace();
                    camera1.release();
                    System.out.println("camera.release");
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            camera1.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    if (success) {
                        initCamera();// 实现相机的参数初始化
                        camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
                    }
                }
            });

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

        // 相机参数的初始化设置
        private void initCamera() {
            parameters = camera1.getParameters();
            parameters.setPictureFormat(PixelFormat.JPEG);
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 1连续对焦
            setDispaly(parameters, camera1);
            camera1.setParameters(parameters);
            camera1.startPreview();
            camera1.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上
        }

        // 控制图像的正确显示方向
        private void setDispaly(Camera.Parameters parameters, Camera camera) {
            if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
                setDisplayOrientation(camera, 90);
            } else {
                parameters.setRotation(90);
            }

        }

        // 实现的图像的正确显示
        private void setDisplayOrientation(Camera camera, int i) {
            Method downPolymorphic;
            try {
                downPolymorphic = camera.getClass().getMethod("setDisplayOrientation", new Class[]{int.class});
                if (downPolymorphic != null) {
                    downPolymorphic.invoke(camera, new Object[]{i});
                }
            } catch (Exception e) {
                Log.e("Came_e", "图像出错");
            }
        }
    }

    class surfaceholderCallbackFont implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // 获取camera对象
            int cameraCount = Camera.getNumberOfCameras();
            if (cameraCount == 2) {
                camera2 = Camera.open(1);
            }
            try {
                // 设置预览监听
                camera2.setPreviewDisplay(holder);
                Camera.Parameters parameters = camera2.getParameters();
                if (MainActivity.this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                    parameters.set("orientation", "portrait");
                    camera2.setDisplayOrientation(90);
                    parameters.setRotation(90);
                } else {
                    parameters.set("orientation", "landscape");
                    camera2.setDisplayOrientation(0);
                    parameters.setRotation(0);
                }
                camera2.setParameters(parameters);
                // 启动摄像头预览
                camera2.startPreview();
                System.out.println("camera.startpreview");
            } catch (IOException e) {
                e.printStackTrace();
                camera2.release();
                System.out.println("camera.release");
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            camera2.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    if (success) {
                        parameters = camera2.getParameters();
                        parameters.setPictureFormat(PixelFormat.JPEG);
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 1连续对焦
                        setDispaly(parameters, camera2);
                        camera2.setParameters(parameters);
                        camera2.startPreview();
                        camera2.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上
                        camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
                    }
                }
            });

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
        // 控制图像的正确显示方向
        private void setDispaly(Camera.Parameters parameters, Camera camera) {
            if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
                setDisplayOrientation(camera, 90);
            } else {
                parameters.setRotation(90);
            }
        }

        // 实现的图像的正确显示
        private void setDisplayOrientation(Camera camera, int i) {
            Method downPolymorphic;
            try {
                downPolymorphic = camera.getClass().getMethod("setDisplayOrientation", new Class[]{int.class});
                if (downPolymorphic != null) {
                    downPolymorphic.invoke(camera, new Object[]{i});
                }
            } catch (Exception e) {
                Log.e("Came_e", "图像出错");
            }
        }
    }

    private LoopViewPager.OnDispatchTouchEventListener mDispatchOnTouchListener = new LoopViewPager.OnDispatchTouchEventListener() {
        @Override
        public void onDispatchKeyEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                viewPager.setLooperPic(false);
            } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                viewPager.setLooperPic(true);
            }
        }
    };
}
