package com.haha.simplenews.module.news;

import com.haha.simplenews.base.BasePresenter;
import com.haha.simplenews.base.BaseView;
import com.haha.simplenews.bean.NewsListBean;

import java.util.List;

/**
 * Created by 格格不入 on 2017/12/14.
 */

public interface NewsListPagerContract {
    interface View extends BaseView{
        /**
         * 展示加载动画
         */
        void showLoading();
        /**
         * 隐藏加载动画
         */
        void hideLoading();

        /**
         * 刷新数据
         */
        void refreshData();
        /**
         * 加载更多
         */
        void loadMore();

        void showData(List<NewsListBean.NewsBean> newsBeans);
        void showLoadMore(List<NewsListBean.NewsBean> newsBeans);
    }

    abstract class Presenter extends BasePresenter<View>{
        /**
         * 加载数据
         */
        public abstract void LoadDataFromNet(int type,int page);
    }
}
