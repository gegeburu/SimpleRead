package com.haha.simplenews.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haha.simplenews.R;
import com.haha.simplenews.adapter.BasePagerAdapter;
import com.haha.simplenews.global.GlobalConfig;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 格格不入 on 2017/12/13.
 *
 * 新闻页
 */

public class NewsFragment extends Fragment{

    @ViewById(R.id.tl_news)
    private TabLayout mTlNews;
    @ViewById(R.id.vp_news)
    private ViewPager mVpNews;

    private List<Fragment> mFragmentList;
    private String[] mTitleList;

    private static NewsFragment instance;
    public static NewsFragment getInstance(){
        if (instance == null){
            instance = new NewsFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ViewUtils.inject(view,this);
        initData();
        initView();
        return view;
    }

    private void initData() {
        mFragmentList = new ArrayList<>();
        mTitleList = new String[]{
                "头条","NBA","汽车","笑话"
        };
        mFragmentList.add(NewsListPager.newInstance(GlobalConfig.HEADLINE));
        mFragmentList.add(NewsListPager.newInstance(GlobalConfig.NBA));
        mFragmentList.add(NewsListPager.newInstance(GlobalConfig.CAR));
        mFragmentList.add(NewsListPager.newInstance(GlobalConfig.JOKE));

    }

    private void initView() {
        mTlNews.setupWithViewPager(mVpNews);
        mVpNews.setOffscreenPageLimit(mFragmentList.size());
        mVpNews.setAdapter(new BasePagerAdapter(getChildFragmentManager(),mFragmentList,mTitleList));
    }
}
