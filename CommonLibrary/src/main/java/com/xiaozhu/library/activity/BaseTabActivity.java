package com.xiaozhu.library.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.xiaozhu.library.R;
import com.xiaozhu.library.adapters.TabPagerAdapter;
import com.xiaozhu.library.fragment.BaseFragment;
import com.xiaozhu.library.interfaces.BaseTabInterface;

import java.util.List;

/**
 * @说明 头部标签选择功能
 * @作者 LY
 * @时间 2018/1/17 10:13
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 LY-版权所有
 * @备注
 */
public class BaseTabActivity extends BaseActivity implements BaseTabInterface {
    protected TabLayout tabView;
    protected ImageView btnAdd;
    protected ViewPager viewPager;
    protected TabPagerAdapter tabPagerAdapter;

    @Override
    public void initView() {
        tabView = this.findViewById(R.id.tabView);
        btnAdd = this.findViewById(R.id.btnAdd);
        viewPager = this.findViewById(R.id.viewPager);
        tabView.setupWithViewPager(viewPager);
    }

    @Override
    public void business() {

    }

    @Override
    public void setTabData(List<String> tabName, List<Fragment> listFragment) {
        if (tabPagerAdapter == null) {
            tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), listFragment, tabName);
            viewPager.setAdapter(tabPagerAdapter);
            viewPager.setOffscreenPageLimit(15);
        } else {
            tabPagerAdapter.recreateItems(listFragment, tabName);
        }
    }

    @Override
    public int getLayoutResID() {
        return R.layout.common_tab_layout;
    }
}
