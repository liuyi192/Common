package com.xiaozhu.library.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaozhu.library.R;
import com.xiaozhu.library.adapters.MyFragmentPagerAdapter;
import com.xiaozhu.library.fragment.CheckAtlasFragment;
import com.xiaozhu.library.widget.custom.ViewPagerFixed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @说明 查看图集
 * @作者 LY
 * @时间 2018/1/23 11:21
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class CheckAtlasActivity extends BaseActivity {
    private static String POSITION = "position";
    private static String IMG_LIST = "imgList";
    private ViewPagerFixed viewPager;
    private TextView tvNumber;
    private ImageView btnClose;

    private int selectPosition = 0;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private List<String> list = new ArrayList<>();


    /**
     * 跳转到查看图集
     *
     * @param context
     * @param position 位置
     * @param list     图集
     */
    public static void startCheckAtlas(Context context, int position, List<String> list) {
        Intent intent = new Intent(context, CheckAtlasActivity.class);
        intent.putExtra(POSITION, position);
        intent.putExtra(IMG_LIST, (Serializable) list);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        viewPager = (ViewPagerFixed) this.findViewById(R.id.viewPager);
        tvNumber = (TextView) this.findViewById(R.id.tvNumber);
        btnClose = (ImageView) this.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void business() {
        selectPosition = getIntent().getIntExtra(POSITION, 0);
        list = (List<String>) getIntent().getSerializableExtra(IMG_LIST);
        tvNumber.setText("（" + (selectPosition + 1) + "/" + list.size() + "）");
        for (int i = 0; i < list.size(); i++) {
            fragmentList.add(CheckAtlasFragment.newInstance(list.get(i)));
        }
        viewPager.setOffscreenPageLimit(fragmentList.size());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(selectPosition);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPosition = position;
                tvNumber.setText("（" + (selectPosition) + "/" + list.size() + "）");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_check_atlas;
    }

}
