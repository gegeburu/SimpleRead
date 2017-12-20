package com.haha.simplenews.module.main;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.haha.simplenews.MyApplication;
import com.haha.simplenews.R;
import com.haha.simplenews.base.MVPBaseActivity;
import com.haha.simplenews.module.news.NewsFragment;
import com.haha.simplenews.module.photo.PhotoFragment;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;

import java.util.ArrayList;

public class MainActivity extends MVPBaseActivity<MainContract.View,
        MainContract.Presenter> implements MainContract.View {

    @ViewById(R.id.draw_layout)
    private DrawerLayout mDrawLayout;
    @ViewById(R.id.nav_view)
    private NavigationView mNavView;
    @ViewById(R.id.toolbar)
    private Toolbar mToolbar;
    @ViewById(R.id.content)
    private FrameLayout mcontent;
    private int currentIndex;
    private ArrayList<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        initData();
        initView();
        //缓存启动图
        cacheImage();
    }

    private void initData() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(NewsFragment.getInstance());
        mFragmentList.add(PhotoFragment.getInstance());
    }

    private void initView() {
        mToolbar.setTitle("新闻");
        //设置开关
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawLayout, mToolbar,
                R.string.draw_open, R.string.draw_close);
        mDrawLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //默认选中新闻
        mNavView.getMenu().getItem(0).setChecked(true);
        //设置menu监听
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news:
                        setIndexSelect(0);
                        mToolbar.setTitle("新闻");
                        break;
                    case R.id.photo:
                        setIndexSelect(1);
                        mToolbar.setTitle("酷图");
                        break;
                }
                mDrawLayout.closeDrawers();
                return true;
            }
        });
        //先添加一次
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content, mFragmentList.get(0)).commit();
    }

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void cacheImage() {
        mPresenter.getImageToCache();
    }
    private void setIndexSelect(int index) {
        //如果是当前页面，返回
        if (currentIndex == index) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragmentList.get(currentIndex));
        //如果不存在 添加
        if (!mFragmentList.get(index).isAdded()) {
            transaction.add(R.id.content, mFragmentList.get(index)).show(mFragmentList.get(index));
        } else {
            //如果存在直接显示
            transaction.show(mFragmentList.get(index));
        }
        currentIndex = index;
        //提交事务
        transaction.commit();

    }
}
