package com.haha.simplenews.module.photo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.haha.simplenews.MyApplication;
import com.haha.simplenews.R;
import com.haha.simplenews.adapter.PhotoAdapter;
import com.haha.simplenews.base.MVPBaseFragment;
import com.haha.simplenews.bean.PhotoBean;
import com.haha.simplenews.global.GlobalConfig;
import com.haha.simplenews.module.photo.photodetail.PhotoDetailActivity;
import com.haha.simplenews.utils.findViewUtil.ViewById;
import com.haha.simplenews.utils.findViewUtil.ViewUtils;
import com.haha.simplenews.view.MyLoadCreator;
import com.haha.simplenews.view.MyRefreshViewCreator;
import com.haha.simplenews.weight.wraprecyclerview.LoadRefreshRecyclerView;
import com.haha.simplenews.weight.wraprecyclerview.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 格格不入 on 2017/12/19.
 *
 * 酷图欣赏
 */

public class PhotoFragment extends MVPBaseFragment<PhotoFragmentContract.View,PhotoFragmentContract.Presenter>
        implements PhotoFragmentContract.View{
    @ViewById(R.id.rv_news)
    LoadRefreshRecyclerView mRvNews;
    @ViewById(R.id.pb_load)
    ProgressBar mPbLoad;

    private List<PhotoBean.ResultsBean> mPhotoList = new ArrayList<>();
    private PhotoAdapter mPhotoAdapter;
    private int mPage = 1;

    private static PhotoFragment instance;
    public static PhotoFragment getInstance(){
        if (instance == null){
            instance = new PhotoFragment();
        }
        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, container, false);
        ViewUtils.inject(view,this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
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
        initData();
        return view;

    }

    private void initData() {
        showLoading();
        mPresenter.LoadDataFromNet(1);
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
        mPresenter.LoadDataFromNet(1);
    }

    @Override
    public void loadMore() {
        mPage += 1;
        mPresenter.LoadDataFromNet(mPage);
    }

    @Override
    public void showData(List<PhotoBean.ResultsBean> resultsBeans) {
        if (resultsBeans == null){
            Toast.makeText(MyApplication.getContext(),"数据加载失败",Toast.LENGTH_SHORT).show();
            if (mPhotoList == null){
                hideLoading();
            }else {
                mRvNews.onStopRefresh();
            }
        }else {
            mPhotoList.clear();
            mPhotoList.addAll(resultsBeans);
            if (mPhotoAdapter == null){
                mPhotoAdapter = new PhotoAdapter(mPhotoList);
                mPhotoAdapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        openPhotoDetailActivity(position);
                    }
                });
                mRvNews.setAdapter(mPhotoAdapter);
                hideLoading();
            }else {
                mRvNews.onStopRefresh();
                mPhotoAdapter.notifyDataSetChanged();
            }

        }
    }

    private void openPhotoDetailActivity(int position) {
        Intent intent = new Intent(MyApplication.getContext(), PhotoDetailActivity.class);
        intent.putExtra(GlobalConfig.PHOTO_OBJECT,mPhotoList.get(position));
        startActivity(intent);
    }

    @Override
    public void showLoadMore(List<PhotoBean.ResultsBean> resultsBeans) {
        mRvNews.onStopLoad();
        mPhotoList.addAll(resultsBeans);
        if (mPhotoAdapter != null){
            mPhotoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected PhotoFragmentContract.Presenter createPresenter() {
        return new PhotoFragmentPresenter();
    }
}
