package com.xiaozhu.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.xiaozhu.common.banner.BannerAdapter;
import com.xiaozhu.common.banner.BannerEntity;
import com.xiaozhu.library.widget.banner.LoopViewPager;
import com.xiaozhu.library.widget.custom.CircleIndicator;

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

        ((TextView) findViewById(R.id.btnWeb)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            }
        });
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
