package com.haha.simplenews.module.news;


import android.text.TextUtils;
import android.widget.Toast;

import com.haha.simplenews.MyApplication;
import com.haha.simplenews.api.WangYiApi;
import com.haha.simplenews.bean.NewsListBean;
import com.haha.simplenews.utils.RetrofitFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 格格不入 on 2017/12/14.
 */

public class NewsListPagerPresenter extends NewsListPagerContract.Presenter {
    @Override
    public void stop() {

    }

    @Override
    public void LoadDataFromNet(int type, final int page) {
        RetrofitFactory.getRetrofit(WangYiApi.HOST)
                .create(WangYiApi.class)
                .getHeadLineList(page)
                .subscribeOn(Schedulers.io())
                .switchMap(new Function<NewsListBean, Observable<NewsListBean.NewsBean>>() {

                    @Override
                    public Observable<NewsListBean.NewsBean> apply(NewsListBean newsListBean) throws Exception {
                        return Observable.fromIterable(newsListBean.getT1348647909107());
                    }
                })
                .filter(new Predicate<NewsListBean.NewsBean>() {//过滤掉没有url的新闻
                    @Override
                    public boolean test(NewsListBean.NewsBean newsBean) throws Exception {
                        if (TextUtils.isEmpty(newsBean.url)){
                            return false;
                        }
                        return true;
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getView().<List<NewsListBean.NewsBean>>bindToLife())
                .subscribe(new Consumer<List<NewsListBean.NewsBean>>() {
                    @Override
                    public void accept(List<NewsListBean.NewsBean> newsBeans) {
                        if (page == 0) {
                            getView().showData(newsBeans);
                        } else {
                            getView().showLoadMore(newsBeans);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        getView().showData(null);
                    }
                });

    }
}

