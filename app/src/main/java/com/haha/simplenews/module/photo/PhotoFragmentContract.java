package com.haha.simplenews.module.photo;

import com.haha.simplenews.base.BasePresenter;
import com.haha.simplenews.base.BaseView;
import com.haha.simplenews.bean.PhotoBean;

import java.util.List;

/**
 * Created by 格格不入 on 2017/12/19.
 */

interface PhotoFragmentContract {
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

        void showData(List<PhotoBean.ResultsBean> resultsBeans);

        void showLoadMore(List<PhotoBean.ResultsBean> resultsBeans);
    }

    abstract class Presenter extends BasePresenter<View>{
        /**
         * 加载数据
         */
        public abstract void LoadDataFromNet(int page);
    }
}
