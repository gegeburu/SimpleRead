package com.haha.simplenews.module.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.haha.simplenews.MyApplication;
import com.haha.simplenews.R;
import com.haha.simplenews.adapter.NewListAdapter;
import com.haha.simplenews.base.LazyLoadFragment;
import com.haha.simplenews.bean.NewsListBean;
import com.haha.simplenews.global.GlobalConfig;
import com.haha.simplenews.module.news.newsdetail.NewsDetailActivity;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;
import com.haha.simplenews.view.MyLoadCreator;
import com.haha.simplenews.view.MyRefreshViewCreator;
import com.haha.simplenews.weight.wraprecyclerview.LoadRefreshRecyclerView;
import com.haha.simplenews.weight.wraprecyclerview.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 格格不入 on 2017/12/14.
 *
 * 新闻列表页
 */

public class NewsListPager extends LazyLoadFragment<NewsListPagerContract.View,
        NewsListPagerContract.Presenter> implements NewsListPagerContract.View{


    @ViewById(R.id.rv_news)
    LoadRefreshRecyclerView mRvNews;
    @ViewById(R.id.pb_load)
    ProgressBar mPbLoad;

    private int mPage = 0;
    private int type;
    private List<NewsListBean.NewsBean> newsList = new ArrayList<>();
    private NewListAdapter newListAdapter;

    public static NewsListPager newInstance(int id){
        NewsListPager instance = new NewsListPager();
        Bundle bundle = new Bundle();
        bundle.putInt(GlobalConfig.NEWS_TYPE,id);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void showLoading() {
        mPbLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoad.setVisibility(View.INVISIBLE);
    }

    @Override
    public void refreshData() {
        mPresenter.LoadDataFromNet(type,0);
    }

    @Override
    public void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             showLoadMore(newsList);
            }
        },1000);
    }

    @Override
    public void showData(List<NewsListBean.NewsBean> newsBeans) {
       if (null == newsBeans){
           Toast.makeText(MyApplication.getContext(),"加载数据失败",Toast.LENGTH_SHORT).show();
           if (newsList != null){
               mRvNews.onStopRefresh();
           }else {
               hideLoading();
           }
       }else {
           newsList.clear();
           newsList.addAll(newsBeans);
           if (newListAdapter == null){
               newListAdapter = new NewListAdapter(newsList);
               newListAdapter.setOnItemClickListener(new NewListAdapter.OnItemClickListener() {
                   @Override
                   public void onItemClick(int position) {
                       openNewsDetailActivity(position);
                   }
               });
               mRvNews.setAdapter(newListAdapter);
               hideLoading();
           }else {
               mRvNews.onStopRefresh();
               newListAdapter.notifyDataSetChanged();
           }


       }
    }

    private void openNewsDetailActivity(int position) {
        Intent intent = new Intent(MyApplication.getContext(), NewsDetailActivity.class);
        intent.putExtra(GlobalConfig.NEWS_OBJECT,newsList.get(position));
        startActivity(intent);
    }

    @Override
    public void showLoadMore(List<NewsListBean.NewsBean> newsBeans) {
        newsList.addAll(newsBeans);
        mRvNews.onStopLoad();
        if (newListAdapter != null){
            newListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected NewsListPagerContract.Presenter createPresenter() {
        return new NewsListPagerPresenter();
    }

    @Override
    protected void initData() {
        showLoading();
        type = getArguments().getInt(GlobalConfig.NEWS_TYPE);
        mPresenter.LoadDataFromNet(type,mPage);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, container, false);
        ViewUtils.inject(view,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvNews.setLayoutManager(layoutManager);
        mRvNews.addRefreshViewCreator(new MyRefreshViewCreator());
        mRvNews.addLoadViewCreator(new MyLoadCreator());
        mRvNews.setOnRefreshListener(new RefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        mRvNews.setOnLoadMoreListener(new LoadRefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoad() {
                loadMore();
            }
        });
        return view;
    }
}
